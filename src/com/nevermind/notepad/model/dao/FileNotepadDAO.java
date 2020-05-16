package com.nevermind.notepad.model.dao;

import com.nevermind.notepad.model.Note;
import com.nevermind.notepad.model.specs.NoteSpecification;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

public class FileNotepadDAO implements NotepadDAO {
    private String fileName = "notepad.txt";
    private ArrayList<Note> notes;

    public FileNotepadDAO() {
        notes = loadFromFile();
        if (notes == null) {
            notes = new ArrayList<>();
        }
    }

    @Override
    public boolean add(String title, String email, String message) {

        if (verify(title, "^[A-Z].{0,19}") &&
                verify(email, "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$") &&
                verify(message, ".{5,200}") &&
                !verify(message, "\\n|\\r|\\f|\\u0085|\\u2029")) {
            return notes.add(new Note(title, LocalDate.now(), email, message));
        } else {
            System.err.println("Не удалось добавить заметку");
            return false;
        }

    }

    private boolean verify(String input, String pattern) {
        System.out.println("input "+ input+" , pattern "+pattern);
        System.out.println(Pattern.matches(pattern, input));
        return Pattern.matches(pattern, input);
    }

    @Override
    public List<Note> readAll() {
        return notes;
    }

    @Override
    public List<Note> search(NoteSpecification spec, boolean sorted) {
        ArrayList<Note> searchResult = new ArrayList<>();
        for (Note note : notes) {
            if (spec.specified(note)) {
                searchResult.add(note);
            }
        }
        if (sorted) {
            searchResult.sort(Comparator.comparing(Note::getDate));
        }
        return searchResult;
    }

    private ArrayList<Note> loadFromFile() {
        ArrayList<Note> inputNotes = new ArrayList<>();
        try (BufferedReader br
                     = new BufferedReader(new FileReader(fileName))) {

            while (br.readLine() != null) {
                Note note;
                String[] noteDetails = new String[4];
                for (int i = 0; i < noteDetails.length; i++) {
                    noteDetails[i] = br.readLine();
                }
                note = initNote(noteDetails);
                inputNotes.add(note);
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Файл не найден");
        } catch (IOException io) {
            System.err.println("Проблема чтения из файла");
        }
        return inputNotes;
    }

    public boolean saveToFile() {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ioe) {
                System.err.println("Не удалось создать новый файл");
                return false;
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Note note : notes) {
                bw.newLine();
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

    public void onExit() {
        saveToFile();
    }
}
