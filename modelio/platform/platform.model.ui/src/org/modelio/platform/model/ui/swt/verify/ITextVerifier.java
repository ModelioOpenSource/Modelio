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
package org.modelio.platform.model.ui.swt.verify;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.events.VerifyEvent;

@objid ("4f52e168-3379-443c-8d62-b28d96bdee0f")
public interface ITextVerifier {
    @objid ("8e485b89-f7c2-4ca3-9be3-11a90869cc8d")
    boolean isValid(String text, VerifyEvent e);

}
