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

package org.modelio.model.browser.view.handlers.properties;

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.expressions.PropertyTester;
import org.modelio.gproject.fragment.FragmentMigrationNeededException;
import org.modelio.gproject.fragment.FragmentState;
import org.modelio.gproject.fragment.IProjectFragment;

/**
 * Core Expression property tester for {@link IProjectFragment}.
 * 
 * @author cmarin
 */
@objid ("03ed7768-3c9a-44d9-befb-619cfecf0fe8")
public class FragmentPropertyTester extends PropertyTester {
    @objid ("3c2e852e-d9c9-47ce-8694-2c569b6b25cd")
    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        IProjectFragment frag = (IProjectFragment) receiver;
        
        switch (property) {
        case "id":
        case "name":
            return frag.getId().equals(expectedValue);
        
        case "downError":
            Throwable downError = frag.getDownError();
            if (downError == null)
                return expectedValue == null;
            else
                return Objects.equals(downError.getClass().getSimpleName(), expectedValue);
        case "needMigration":
            boolean needMigration = frag.getDownError() instanceof FragmentMigrationNeededException;
            return boolCompare(needMigration, expectedValue);
            
        case "state":
            return frag.getState().name().equalsIgnoreCase(expectedValue.toString());
            
        case "isUp":
            boolean isUp = frag.getState() == FragmentState.UP_FULL || frag.getState() == FragmentState.UP_LIGHT;
            return boolCompare(isUp, expectedValue); 
            
        case "isDown":
            return boolCompare(frag.getState() == FragmentState.DOWN, expectedValue); 
        
        case "type":
            return frag.getType().name().equalsIgnoreCase(expectedValue.toString());
            
        case "scope":
            return frag.getScope().name().equalsIgnoreCase(expectedValue.toString());
            
        case "property":
            String value = frag.getProperties().getValue(args[0].toString());
            return Objects.equals(value, expectedValue);
            
        default:
            throw new IllegalArgumentException(property);
        
        }
    }

    @objid ("db4a31c3-f71e-4026-907f-5ce4d49996f8")
    private boolean boolCompare(boolean value, Object expectedValue) {
        if (expectedValue==null)
            return value;
        else
            return expectedValue.equals(value);
    }

}
