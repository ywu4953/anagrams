/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    private static ArrayList<String> wordList= new ArrayList<>();
    private static HashSet<String> wordSet = new HashSet<>();
    private static HashMap<String, ArrayList<String>> lettersToWord = new HashMap<>();

    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);
            wordSet.add(sortLetters(word));
            if (!lettersToWord.containsKey(sortLetters(word))){
                ArrayList<String> newlist = new ArrayList<>();
                newlist.add(word);
                lettersToWord.put(sortLetters(word), newlist);
            }
            else{
                ArrayList<String> newList = lettersToWord.get(sortLetters(word));
                newList.add(word);
                lettersToWord.put(sortLetters(word), newList);
            }
        }
    }

    public boolean isGoodWord(String word, String base) {
        if (wordSet.contains(word) && !word.contains(base)){
            return true;
        }
        return false;
    }

    public String sortLetters(String word) {
        char [] arr= word.toCharArray();
        Arrays.sort(arr);
        return Arrays.toString(arr);
    }

    public List<String> getAnagrams(String targetWord) {
      //  ArrayList<String> result = new ArrayList<String>();
     /*   for (String word: wordList){
            if (word.length() == targetWord.length() && sortLetters(targetWord).equals(sortLetters(word))){
                result.add(word);
            }
        } */

        if (wordSet.contains(sortLetters(targetWord))){
            return lettersToWord.get(sortLetters(targetWord));

        }

        else
            return null;
    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        for (char c = 'a'; c <= 'z'; c++){
            String newWord = word + c;
            if (lettersToWord.containsKey(sortLetters(newWord))){
                result.addAll(lettersToWord.get(sortLetters(newWord)));
            }
        }
        return result;
    }

    public String pickGoodStarterWord() {
    /*    int minAnagrams = 0;
        String result = wordList.get(0);
        for (String s: wordList){
            minAnagrams = Math.min(minAnagrams, wordList.get(sortLetters()))
        }*/
        Random rand = new Random();
        while(true){
            int index = rand.nextInt(wordList.size());
            String s = wordList.get(index);
            if (lettersToWord.get(sortLetters(s)).size()>= MIN_NUM_ANAGRAMS){
                return s;
            }
        }
      //  return "skate";
    }
}
