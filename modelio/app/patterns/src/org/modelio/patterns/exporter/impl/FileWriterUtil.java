/* 
 * Copyright 2013-2018 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.patterns.exporter.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.patterns.plugin.Patterns;

@objid ("81bd745e-f25c-425a-9bc6-9ac4b2fe7853")
class FileWriterUtil implements AutoCloseable {
    @objid ("3a4fe736-488b-4e7e-a03e-55533782cbea")
    private int counter = 0;

    @objid ("df850845-fa7f-4f4a-99c4-62320c72c340")
    private int methodIndex = 1;

    @objid ("dc2560c6-4861-4d4e-8c5b-89eee97286da")
    private BufferedWriter hWriter;

    @objid ("ce5d42df-1668-4cfc-9c3a-ff852a0df358")
    public int getCounter() {
        return this.counter;
    }

    @objid ("c7e4a80d-ef73-4d22-a8d4-789f19e703be")
    public int getMethodIndex() {
        return this.methodIndex;
    }

    @objid ("d13ba72b-858d-4002-a909-393c19a599ed")
    public void write(List<String> contents) {
        if (this.hWriter == null) {
            return;
        }
        try {
            Iterator<String> iterator = contents.iterator();
            while (iterator.hasNext()) {
                String str = iterator.next();
                if (str != null) {
                    this.hWriter.write(str);
                    this.hWriter.newLine();
                }
            }
        } catch (IOException e) {
            Patterns.LOG.debug(e);
        }
    }

    @objid ("0f096d26-eb93-4a93-b4d4-65d9aea6c153")
    public void write(String contents) {
        if (this.hWriter == null) {
            return;
        }
        try {
            if (contents != null) {
                this.hWriter.write(contents);
                this.hWriter.newLine();
            }
        } catch (IOException e) {
            Patterns.LOG.debug(e);
        }
    }

    @objid ("98de30f0-ba8f-408d-b434-4283c19f047b")
    public void countWrite(String contents) {
        if (this.hWriter == null) {
            return;
        }
        try {
            if (this.counter == 0) {
                this.hWriter.write("private void createModel" + (this.methodIndex) + "()throws Exception {");
                this.hWriter.newLine();
            }
        
            if (contents != null) {
                if ((this.counter < 1000)) {
                    this.hWriter.write(contents);
                    this.hWriter.newLine();
                    this.counter += 1;
                } else {
                    this.hWriter.write("    }");
                    this.hWriter.newLine();
                    this.methodIndex += 1;
                    this.hWriter.write("private void createModel" + (this.methodIndex) + "()throws Exception {");
                    this.hWriter.newLine();
                    this.hWriter.write(contents);
                    this.hWriter.newLine();
                    this.counter = 1;
                }
            }
        } catch (IOException e) {
            Patterns.LOG.debug(e);
        }
    }

    @objid ("6555e2ab-90f1-421f-b657-e022eca4f8fd")
    @Override
    public void close() {
        if (this.hWriter == null) {
            return;
        }
        try {
            this.hWriter.close();
        } catch (IOException e) {
            Patterns.LOG.debug(e);
        }
    }

    @objid ("4b7093e7-01cc-4484-b1fa-f15b8c1c7f9e")
    public FileWriterUtil(Path path) {
        if (path != null) {
            try {
                if (Files.exists(path)) {
                    Files.delete(path);
                }
                this.hWriter = new BufferedWriter(new FileWriter(path.toFile()));
            } catch (IOException e) {
                Patterns.LOG.debug(e);
            }
        }
        this.counter = 0;
        this.methodIndex = 1;
    }

}
