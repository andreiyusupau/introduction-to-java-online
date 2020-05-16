package com.nevermind.notepad.model.specs;

import com.nevermind.notepad.model.Note;

import java.util.ArrayList;

public class NoteSpecificationConjunction implements NoteSpecification {
ArrayList<NoteSpecification> specs;

    public NoteSpecificationConjunction() {
        specs=new ArrayList<>();
    }
    public NoteSpecificationConjunction(ArrayList<NoteSpecification> specs) {
        this.specs = specs;
    }

    public void add(NoteSpecification spec){
specs.add(spec);
    }

    @Override
    public boolean specified(Note note) {

       for(NoteSpecification spec:specs){
         if(!spec.specified(note))  {
             return false;
         }
       }
       return true;
    }
}
