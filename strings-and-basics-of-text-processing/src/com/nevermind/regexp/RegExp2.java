package com.nevermind.regexp;

import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

      /*2. Дана строка, содержащая следующий текст (xml-документ):
<notes>
 <note id = "1">
 <to>Вася</to>
 <from>Света</from>
 <heading>Напоминание</heading>
    <body>Позвони мне завтра!</body>
 </note>
 <note id = "2">
 <to>Петя</to>
 <from>Маша</from>
    <heading>Важное напоминание</heading>
 <body/>
 </note>
</notes>
    Напишите анализатор, позволяющий последовательно возвращать содержимое узлов xml-документа и его тип (открывающий
    тег, закрывающий тег, содержимое тега, тег без тела). Пользоваться готовыми парсерами XML для решения данной задачи
    нельзя.*/


public class RegExp2 {

    public static void main(String[] args) {

        String text = "<notes>\n" +
                "<note id = \"1\">\n" +
                "<to>Вася</to>\n" +
                "<from>Света</from>\n" +
                "<heading>Напоминание</heading>\n" +
                "<body>Позвони мне завтра!</body>\n" +
                "</note>\n" +
                "<note id = \"2\">\n" +
                "<to>Петя</to>\n" +
                "<from>Маша</from>\n" +
                "<heading>Важное напоминание</heading>\n" +
                "<body/>\n" +
                "</note>\n" +
                "</notes>";

        parseXML(text);
    }

    public static void parseXML(String xml) {
        //TreeMap необходим для упорядочивания полученных значений тегов
        TreeMap<Integer, String> blocks = new TreeMap<>();

        //регулярные выражения для всех видов тегов
        String openTag = "<[^/]>|<([^/].*?[^/])>"; //открывающий тег(первая часть нужна, чтобы распознавать случаи с одной буквой внутри тега)
        String closeTag = "</.+?>"; //закрывающий тег
        String nobodyTag = "<.+?/>"; //тег без тела
        String tagContent = ">(.+?)<"; //содержимое тега

        //добавляем все теги в TreeMap
        searchTag(openTag, blocks, xml, false, "открывающий тег");

        searchTag(closeTag, blocks, xml, false, "закрывающий тег");

        searchTag(nobodyTag, blocks, xml, false, "тег без тела");

        searchTag(tagContent, blocks, xml, true, "содержимое тега");

        //выводим на печать
        for (String s : blocks.values()) {
            System.out.println(s);
        }
    }

    private static void searchTag(String regExp, TreeMap<Integer, String> tm, String text, boolean content, String tagName) {
        //Создаем паттерн
        Pattern pattern;
        pattern = Pattern.compile(regExp);

        //создаем матчер
        Matcher matcher;
        matcher = pattern.matcher(text);//инициализируем матчер для нукжного тега

        int id;
        id = content ? 1 : 0; //Для случая , когда нам необходимо содержимое в скобкам ставим content=true

   /*Все найденные открывающие теги помещаем в TreeMap.
        Уникальным ключом в данном случае является позиция первого символа слова, это и поможет упорядочить теги*/
        while (matcher.find()) {
            tm.put(matcher.start(), matcher.group(id) + " [" + tagName + "]");
        }
    }
}

