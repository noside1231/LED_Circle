import org.json.JSONArray;

/**
 * Created by edisongrauman on 11/15/19.
 */
public class NoteContainer {

    private Note[] notes;
    private int currentNote = 5;

    public NoteContainer(int noteAmount) {

        notes = new Note[noteAmount];
        for (int i = 0 ;i < noteAmount; i++) {
            notes[i] = new Note(i);
        }

    }

    public Note getCurrentNote() {
        return notes[currentNote];
    }

    public Note getNote(int i) {
        return notes[i];
    }

    public void setCurrentNote(int currentNote) {
        this.currentNote = currentNote;
    }

    public void decrementCurrentNote() {
        if (currentNote > 0)
        currentNote--;
    }
    public void incrementCurrentNote() {
        if (currentNote < notes.length-1)
            currentNote++;
    }

    public void setNote(Note n) {
        n.setNoteNumber(currentNote);
        notes[currentNote] = n;
    }

    public void copyNote(int to, int from) {
        notes[to].loadNote(notes[from].saveNote());
    }

    public JSONArray saveNotes() {
        JSONArray arr = new JSONArray();
        for (int i = 0; i < notes.length; i++) {
            arr.put(notes[i].saveNote());
        }
        return arr;
    }

    public void loadNotes(JSONArray arr) {
        try {
            for (int i = 0; i < notes.length; i++) {
                notes[i].loadNote(arr.getJSONObject(i));
            }
        } catch (Exception e) {}
    }
}
