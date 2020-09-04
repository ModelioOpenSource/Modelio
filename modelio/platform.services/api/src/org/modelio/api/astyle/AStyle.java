/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.astyle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidParameterException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * AStyle contains methods to call the Artistic Style formatter(AStylej library).
 */
@objid ("005d4468-13d7-1f63-9ca6-001e4fea2d8b")
public final class AStyle {
    /**
     * Constant for default C++ indentation.
     */
    @objid ("005d5e6c-13d7-1f63-9ca6-001e4fea2d8b")
    public static final String CPLUS = "style=linux mode=c";

    /**
     * Constant for default C# indentation.
     */
    @objid ("005d7a78-13d7-1f63-9ca6-001e4fea2d8b")
    public static final String CSHARP = "style=java mode=cs";

    /**
     * Constant for default Java indentation.
     */
    @objid ("005d90da-13d7-1f63-9ca6-001e4fea2d8b")
    public static final String JAVA = "style=java mode=java";

    @objid ("005da73c-13d7-1f63-9ca6-001e4fea2d8b")
    private static final int BUFFERSIZE = 1024;

    /**
     * Call the AStyleMain function in Artistic Style.
     * 
     * @param textIn A string containing the source code to be formatted.
     * @param options A string of options to Artistic Style.
     * @return A String containing the formatted source from Artistic Style, or an empty string on error.
     */
    @objid ("005db56a-13d7-1f63-9ca6-001e4fea2d8b")
    public static String format(final String textIn, final String options) {
        AStyleInterface astyle = new AStyleInterface();
        String textOut = astyle.formatSource(textIn, options);
        if (textOut.isEmpty()) {
            throw new InvalidParameterException();
        } else {
            return textOut;
        }
    }

    /**
     * Call the AStyleMain function in Artistic Style.
     * 
     * @param inputFile A file containing the source code to be formatted.
     * @param outputFile The file which will contain the formatted source from Artistic Style, or an empty string on error.
     * @param options A string of options to Artistic Style.
     * @throws java.io.IOException Occurs when the input or output files are invalid or can't be accessed.
     */
    @objid ("005dd874-13d7-1f63-9ca6-001e4fea2d8b")
    public static void format(final File inputFile, final File outputFile, final String options) throws IOException {
        String textIn = readFile(inputFile);
        String textOut = format(textIn, options);
        
        writeFile(textOut, outputFile);
    }

    /**
     * This is a pure service class, it can't be instanciated.
     */
    @objid ("005dfb38-13d7-1f63-9ca6-001e4fea2d8b")
    private AStyle() {
    }

    @objid ("005e1488-13d7-1f63-9ca6-001e4fea2d8b")
    private static String readFile(final File inputFile) throws IOException {
        // create input buffers
        int readSize = BUFFERSIZE;
        StringBuffer bufferIn = new StringBuffer(readSize);
        char fileIn[] = new char[readSize];
        
        // read file data
        try (BufferedReader in = new BufferedReader(new FileReader(inputFile))) {
            // use read to preserve the current line endings
            int charsIn = in.read(fileIn, 0, readSize);
            while (charsIn != -1) {
                bufferIn.append(fileIn, 0, charsIn);
                charsIn = in.read(fileIn, 0, readSize);
            }
        }
        return bufferIn.toString();
    }

    @objid ("005e30c6-13d7-1f63-9ca6-001e4fea2d8b")
    private static void writeFile(final String textOut, final File outFile) throws IOException {
        if (outFile.exists()) 
            outFile.delete(); // remove existing file
        
        // write the output file
        try (BufferedWriter out = new BufferedWriter(new FileWriter(outFile))) {
            out.write(textOut, 0, textOut.length());
        }
    }

}
