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

package com.sun.star.comp.beans;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * indicates that an operation needed a document, but no document was loaded.
 * 
 * @since OOo 2.0.0
 */
@objid ("da677c75-f2e4-405f-b0a8-dc4defaea6ef")
public class NoDocumentException extends Exception {
    @objid ("423a6cd0-a227-4922-8350-d67d23d9e9f4")
    private static final long serialVersionUID = 1L;

}
