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

package org.modelio.core.ui.swt.verify;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.events.VerifyEvent;

/**
 * Check that the text after the event matches a specific regex.
 */
@objid ("c089049d-d754-445f-87ba-3f92f8b024e5")
public class RegexVerifier implements ITextVerifier {
    @objid ("4e7c7ea6-c32e-4486-ba94-07488924e7a1")
    protected String regex;

    @objid ("c91c5533-ee18-40bb-a648-95c651277039")
    @Override
    public boolean isValid(String text, VerifyEvent e) {
        StringBuilder builder = new StringBuilder(text);
        builder.replace(e.start, e.end, e.text);
        return builder.length() == 0 || builder.toString().matches(this.regex);
    }

    /**
     * Build a new instance of RegexValidator.
     * 
     * @param regex the regular expression to be matched during validation.
     */
    @objid ("d7eead28-9a1c-4037-9797-4868268b54d4")
    public RegexVerifier(String regex) {
        this.regex = regex;
    }

}
