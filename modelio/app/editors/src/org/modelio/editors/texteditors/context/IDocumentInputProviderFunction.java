/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.editors.texteditors.context;

import java.io.File;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.modelio.editors.texteditors.input.FileDocumentInput;
import org.modelio.editors.texteditors.input.IInput.Listener;
import org.modelio.editors.texteditors.input.IInput;

@objid ("7b502691-2a77-11e2-9fb9-bc305ba4815c")
public class IDocumentInputProviderFunction extends ContextFunction {
    @objid ("7b502692-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public Object compute(IEclipseContext context) {
        final MPart part = context.get(MPart.class);
        final String uri = part.getPersistedState().get("inputURI");
        File file = new File(uri);
        FileDocumentInput input = new FileDocumentInput(file);
        
        input.addListener(new Listener() {
        
            @Override
            public void propertyChanged(String property, Object oldValue, Object newValue) {
                if (IInput.DIRTY == property) {
                    part.setDirty((Boolean) newValue);
                }
            }
        });
        return input;
    }

}
