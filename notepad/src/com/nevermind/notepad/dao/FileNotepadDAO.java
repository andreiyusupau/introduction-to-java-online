package com.nevermind.notepad.dao;

import com.nevermind.notepad.model.Note;
import com.nevermind.notepad.model.specs.NoteSpecification;
import com.nevermind.notepad.util.Util;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//DAO для работы с файлами
public class FileNotepadDAO implements NotepadDAO {
    private final String fileName = "notepad.txt"; //название файла
    private ArrayList<Note> notes; //список заметок

    //конструктор (загружаем заметки из файла)
    public FileNotepadDAO() {
        notes = loadFromFile();
        if (notes == null) {
            notes = new ArrayList<>();
        }
    }

    //добавить заметку
    @Override
    public boolean add(String title, String email, String message) {

        //проверяем н соответстиве шаблонам
        if (Util.verify(title, "^[A-Z].{0,19}") &&
                Util.verify(email, "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$") &&
                Util.verify(message, ".{5,200}") &&
                !Util.verify(message, "\\n|\\r|\\f|\\u0085|\\u2029")) {
            return notes.add(new Note(title, LocalDate.now(), email, message)); //если ок, то доабвляем
        } else {
            System.err.println("Не удалось добавить заметку");
            return false;
        }

    }

    //получить все заметки
    @Override
    public List<Note> readAll() {
        return notes;
    }

    //поиск по списку условию (используется паттерн Specification)
    @Override
    public List<Note> search(NoteSpecification spec, boolean sorted) {
        ArrayList<Note> searchResult = new ArrayList<>();
        for (Note note : notes) {
            if (spec.specified(note)) { //если запись удовлетворяет запросу, добавляем ее в список
                searchResult.add(note);
            }
        }
        if (sorted) {
            searchResult.sort(Comparator.comparing(Note::getDate)); //если необходима сортирвока, сортируем по дате
        }
        return searchResult;
    }

    //загрузка из файла
    private ArrayList<Note> loadFromFile() {
        ArrayList<Note> inputNotes = new ArrayList<>(); //создаем список

        //используем try-with-resources для автомаетического закрытия потока ввода
        try (BufferedReader br
                     = new BufferedReader(new FileReader(fileName))) {
            String currLine;
            //считываем построчно и заносим параметры заметки в объект
            while ((currLine = br.readLine()) != null) {
                Note note;
                String[] noteDetails = new String[4];
                noteDetails[0] = currLine;
                noteDetails[1] = br.readLine();
                noteDetails[2] = br.readLine();
                noteDetails[3] = br.readLine();
                note = initNote(noteDetails);
                inputNotes.add(note); //объект добавляем в список
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Файл не найден");
        } catch (IOException io) {
            System.err.println("Проблема чтения из файла");
        }
        return inputNotes;
    }

    //сохранение в файл
    public boolean saveToFile() {
        File file = new File(fileName);

        //если файла не существует - создаем
        try {
            file.createNewFile();
        } catch (IOException ioe) {
            System.err.println("Не удалось создать новый файл");
            return false;
        }

        //используем try-with-resources для автомаетического закрытия потока вывода
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {

            boolean first = true;

            //записываем все заметки в файл(для первой не используем перенос строки)
            for (Note note : notes) {
                if (first) {
                    first = false;
                } else {
                    bw.newLine();
                }
                bw.write(note.getTitle());
                bw.newLine();
                bw.write(note.getDate().toString());
                bw.newLine();
                bw.write(note.getEmail());
                bw.newLine();
                bw.write(note.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл");
            return false;
        }
        return true;
    }

    //метод для более удобного создания заметок из массива данных
    private Note initNote(String[] noteDetails) {
        String title;
        title = noteDetails[0];
        LocalDate date;
        date = LocalDate.parse(noteDetails[1]);
        String email;
        email = noteDetails[2];
        String message;
        message = noteDetails[3];

        return new Note(title, date, email, message);
    }

    //действия при выходе и программы
    public void onExit() {
        saveToFile();
    }
}
