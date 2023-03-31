/* 
 * Copyright 2013-2020 Modeliosoft
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
package org.modelio.gproject.migration;

import java.io.PrintWriter;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.log.IBasicLogger;

@objid ("38b9375f-12fd-4913-b7e7-e1a363cb06aa")
final class NestedBasicLogger implements IBasicLogger {
    @objid ("8b93fa9e-df71-40e2-a73a-cd6ac4977744")
    private static final String PREFIX = "  > log ";

    @objid ("0d51e035-8f5d-42d4-9bb1-5885357aea8a")
    private IBasicLogger wrapped;

    @objid ("b0ffb5b3-8da7-4654-b096-6082ce95cc0d")
    private PrintWriter writer;

    @objid ("9bedfbf3-43c0-4e66-a0e8-28446603ed97")
    public  NestedBasicLogger(IBasicLogger oldLog, PrintWriter logger) {
        this.wrapped = oldLog;
        this.writer = logger;
        
    }

    @objid ("4762511f-3251-49b0-a079-e565ac0fa8d9")
    @Override
    public void warning(String message) {
        this.wrapped.warning(message);
        
        this.writer.println(NestedBasicLogger.PREFIX + "WARNING: " + message);
        
    }

    @objid ("70fc9643-fd79-49f4-942b-9b62c9f03800")
    @Override
    public void warning(Throwable ex) {
        this.wrapped.warning(ex);
        
        this.writer.append(NestedBasicLogger.PREFIX);
        this.writer.append("WARNING: ");
        
        ex.printStackTrace(this.writer);
        this.writer.println();
        
    }

    @objid ("b9b4f455-1202-47cb-8f9d-c0eecc8b0859")
    @Override
    public void warning(String format, Object... args) {
        this.wrapped.warning(format, args);
        
        this.writer.format(NestedBasicLogger.PREFIX + "WARNING: " + format + "%n", args);
        
    }

    @objid ("45e65821-4521-4da1-a502-8154710b0320")
    @Override
    public void trace(Throwable ex) {
        this.wrapped.trace(ex);
        
        this.writer.append(NestedBasicLogger.PREFIX + "trace: ");
        
        ex.printStackTrace(this.writer);
        this.writer.println();
        
    }

    @objid ("b0626b68-f812-43cb-8575-51c0f34ef152")
    @Override
    public void trace(String format, Object... args) {
        this.wrapped.trace(format, args);
        
        this.writer.format(NestedBasicLogger.PREFIX + "trace: " + format + "%n", args);
        
    }

    @objid ("7620f634-67c4-451e-ac9f-15ae5e61b57a")
    @Override
    public void trace(String message) {
        this.wrapped.trace(message);
        
        this.writer.println(NestedBasicLogger.PREFIX + "trace: " + message);
        
    }

    @objid ("c3d51d87-8437-4ee8-8c11-8a5042d7a24e")
    @Override
    public void error(Throwable ex) {
        this.wrapped.error(ex);
        
        this.writer.append(NestedBasicLogger.PREFIX + "ERROR: ");
        
        ex.printStackTrace(this.writer);
        this.writer.println();
        
    }

    @objid ("c94797eb-5dd5-444c-9299-ea77e7613642")
    @Override
    public void error(String format, Object... args) {
        this.wrapped.error(format, args);
        
        this.writer.format(NestedBasicLogger.PREFIX + "ERROR: " + format + "%n", args);
        
    }

    @objid ("8e858af3-9013-40c6-a998-a78a317bd449")
    @Override
    public void error(String message) {
        this.wrapped.error(message);
        
        this.writer.println(NestedBasicLogger.PREFIX + "ERROR: " + message);
        
    }

    @objid ("8e7ee003-3438-47a1-9db9-454a0adac669")
    @Override
    public int getLevel() {
        return IBasicLogger.TRACE;
    }

    @objid ("dff3434f-d7d0-4145-a020-4e45a549f401")
    @Override
    public void setLevel(int level) {
        // Ignored
    }

}
