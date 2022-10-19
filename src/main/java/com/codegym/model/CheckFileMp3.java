package com.codegym.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CheckFileMp3 {
        private static final String FILE_MUSIC = "[\\w\\s()-.]*[.]mp3";

        public static boolean checkFile(String file){

                if (Pattern.compile(FILE_MUSIC).matcher(file).matches()) {
                    return true;
                }

            return false;
        }
}
