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
package org.modelio.bpmnxml.utils;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("bf9db87c-8c73-4da7-920f-16be786b5de0")
public class StringConvertor {
    @objid ("e6bedcd2-1a17-456f-97a3-5e5974b8e220")
    public static String imports(String val) {
        String result = val;
        result = result.replaceAll("\r\n", "\r");
        result = result.replaceAll("\n", "\r");
        return result;
    }

    @objid ("f8535012-a902-45bf-9781-59c7283167aa")
    public static String exports(String val) {
        return val;
    }

}
