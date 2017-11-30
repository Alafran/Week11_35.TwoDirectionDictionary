package dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MindfulDictionary {

    private Map<String, String> dictionary;
    private File file;

    public MindfulDictionary() {
        this.dictionary = new HashMap<String, String>();
    }

    public MindfulDictionary(String file) {
        this.dictionary = new HashMap<String, String>();
        this.file = new File(file);
    }

    public void add(String word, String translation) {
        if (this.dictionary.keySet().contains(word)) {
            return;
        }
        this.dictionary.put(word, translation);
    }

    public String translate(String word) {
        for (String key : this.dictionary.keySet()) {
            if (key.equals(word)) {
                return this.dictionary.get(key);
            }
        }
        for (String value : this.dictionary.values()) {
            if (value.equals(word)) {
                for (String key : this.dictionary.keySet()) {
                    if (this.dictionary.get(key).equals(value)) {
                        return key;
                    }
                }
            }
        }
        return null;
    }

    public void remove(String word) {
        List<String> wordsToBeRemoved = new ArrayList<String>();
        for (String key : this.dictionary.keySet()) {
            if (key.equals(word)) {
                wordsToBeRemoved.add(key);
            }
        }

        for (String value : this.dictionary.values()) {
            if (value.equals(word)) {
                for (String key : this.dictionary.keySet()) {
                    if (this.dictionary.get(key).equals(value)) {
                        wordsToBeRemoved.add(key);
                    }
                }
            }
        }

        for (String words : wordsToBeRemoved) {
            this.dictionary.remove(words);
        }
    }

    public boolean load() {
        try {
            Scanner reader = new Scanner(this.file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split(":");   // the line is split at :
                
                this.dictionary.put(parts[0], parts[1]);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean save() {
        try{
            FileWriter writer = new FileWriter(file);
            for(String key : this.dictionary.keySet()) {
                writer.write(key + ":" + this.dictionary.get(key) +"\n");
            }
            writer.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
