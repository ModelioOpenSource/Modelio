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

package org.modelio.core.ui.expression;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.expressions.PropertyTester;
import org.modelio.vcore.smkernel.mapi.MAttribute;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MStatus;

/**
 * Eclipse Core Expression property tester for {@link MObject}.
 * <p>
 * <b>Properties:</b><ul>
 * <li> <b>attribute</b> : test attribute value against expected value. The attribute name is given as argument.
 * <li> <b>dep.count.empty</b> : test the dependency is emptiness against expected value.
 * <li> <b>dep.count.eq</b> : test the dependency count equals expected value.
 * <li> <b>dep.count.gt</b> : test the dependency count greater than expected value.
 * <li> <b>dep.count.lt</b> : test the dependency count lower than expected value.
 * <li> <b>modifiable</b> : <i>expectedValue</i> if the object is modifiable
 * <li> <b>shell</b> : <i>expectedValue</i> if the object is shell.
 * <li> <b>status.and</b> : test the object status against the {@link MStatus} methods given as arguments.<br>
 * If the method name begins with "!", the result is negated for this method.<br>
 * The result is combined with the AND operator.
 * <li> <b>status.or</b> : test the object status against the {@link MStatus} methods given as arguments.<br>
 * If the method name begins with "!", the result is negated for this method.<br>
 * The result is combined with the AND operator.
 * </ul>
 * For boolean tests, <i>expectedValue</i> must be a boolean or <i>null</i>, meaning <i>true</i>.
 * <p>
 * For dependency tests, the dependency name is given as argument.
 * @author cmarin
 */
@objid ("b2f914f1-7077-44ed-8340-c095b2072e56")
public class MObjectPropertyTester extends PropertyTester {
    @objid ("f0088f1f-ba2d-4a60-9201-1a99a93d45f2")
    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        MObject obj = (MObject) receiver;
        
        switch (property) {
        case "attribute":
            return testAttribute(args, expectedValue, obj);
        
        case "dep.count.eq":
            return testDepCountEq(args, expectedValue, obj);
        
        case "dep.count.empty":
            return testDepCountEmpty(args, obj);
        
        case "dep.count.gt":
            return testDepCountGreater(args, expectedValue, obj);
        
        case "dep.count.lt":
            return testDepCountLower(args, expectedValue, obj);
        
        case "modifiable":
            return toBoolean(expectedValue) == obj.isModifiable();
        
        case "shell":
            return toBoolean(expectedValue) == obj.isShell();
        
        case "status.and":
            return toBoolean(expectedValue) == testStatusAnd(args, obj);
        
            
        case "status.or":
            return toBoolean(expectedValue) == testStatusOr(args, obj);
            
        default:
            throw new IllegalArgumentException(property);
        }
    }

    @objid ("9721e8a6-3b43-48a2-b574-8846e7b6b5ea")
    private boolean testAttribute(Object[] args, Object expectedValue, MObject obj) throws IllegalArgumentException {
        MAttribute att = getMAtt(obj, args);
        Object val = obj.mGet(att);
        if (expectedValue == null)
            return val != null;
        else
            return Objects.equals(String.valueOf(expectedValue), String.valueOf(val));
    }

    @objid ("573f3076-61a7-4aac-bb3c-1d30900dca2a")
    private static boolean toBoolean(Object value) {
        if (value == null)
            return true;
        else if (value instanceof Integer)
            return ((Integer)value) > 0;
        return (Boolean) value;
    }

    @objid ("c412b587-ec17-4cf1-8014-e2bd4991618f")
    private boolean testStatusAnd(Object[] args, MObject obj) {
        MStatus status = obj.getStatus();
        
        for (Object arg : args) {
            String mname = (String) arg;
            if (testBooleanMethod(status, mname))
                return false;
        }
        return true;
    }

    @objid ("68f98331-3be8-4f86-b4ee-90bee450c17c")
    private boolean testStatusOr(Object[] args, MObject obj) {
        MStatus status = obj.getStatus();
        
        for (Object arg : args) {
            String mname = (String) arg;
            
            if (testBooleanMethod(status, mname))
                return true;
        }
        return false;
    }

    /**
     * Call a boolean method on the given java object and test its return value.
     * <p>
     * The method name may begin with "!", the result is then negated.
     * 
     * @param receiver the object to test
     * @param methodName the method name to call. If the method name begins with "!", the result is negated.
     * @return the test result
     */
    @objid ("fefab098-ec61-4af6-b6cd-de17ac2c9eaf")
    private boolean testBooleanMethod(Object receiver, String methodName) {
        String mname = methodName;
        boolean expected = true;
        if (mname.startsWith("!")) {
            expected = false;
            mname = mname.substring(1).trim();
        }
        
        final Method method;
        try {
            method = receiver.getClass().getMethod(mname);
        } catch ( NoSuchMethodException | SecurityException e) {
            throw new IllegalArgumentException("Bad method name '"+mname+"': "+e.toString(), e);
        }        
        
        try {
            Boolean val = (Boolean) method.invoke(receiver);
            return (val.booleanValue() == expected);
        } catch (IllegalAccessException |  InvocationTargetException e) {
            throw new IllegalArgumentException(e.toString(), e);
        }
    }

    @objid ("0df9fcc1-cf0c-4b75-8a62-9c5727a457f9")
    private boolean testDepCountEq(Object[] args, Object expectedValue, MObject obj) throws IllegalArgumentException {
        MDependency dep = getMDep(obj, args);
        List<MObject> vals = obj.mGet(dep);
        if (expectedValue == null)
            throw new IllegalArgumentException("Missing expected value.");
        else if (expectedValue instanceof Integer)
            return vals.size() == (Integer)expectedValue;
        else
            throw new IllegalArgumentException("Unsupported value:"+expectedValue);
    }

    @objid ("d4d27df3-5d7a-459e-b7f5-d81436b2fcc8")
    private boolean testDepCountEmpty(Object[] args, MObject obj) throws IllegalArgumentException {
        MDependency dep = getMDep(obj, args);
        List<MObject> vals = obj.mGet(dep);
        return vals.isEmpty();
    }

    @objid ("56646299-858a-4441-b3ae-0b8639843e7e")
    private boolean testDepCountGreater(Object[] args, Object expectedValue, MObject obj) throws IllegalArgumentException {
        MDependency dep = getMDep(obj, args);
        List<MObject> vals = obj.mGet(dep);
        if (expectedValue == null)
            return !vals.isEmpty();
        else 
            return vals.size() > (Integer)expectedValue;
    }

    @objid ("a42ae0b3-3ac4-4fd0-84c1-7b84af78d6af")
    private boolean testDepCountLower(Object[] args, Object expectedValue, MObject obj) throws IllegalArgumentException {
        MDependency dep = getMDep(obj, args);
        List<MObject> vals = obj.mGet(dep);
        if (expectedValue == null)
            throw new IllegalArgumentException("Missing expected value.");
        else 
            return vals.size() < (Integer)expectedValue;
    }

    @objid ("2b0b5500-b9d0-4036-b501-5c2b2cd479d6")
    private static MAttribute getMAtt(MObject obj, Object[] args) throws IllegalArgumentException {
        MAttribute att = obj.getMClass().getAttribute((String) args[0]);
        if (att != null)
            return att;
        
        throw new IllegalArgumentException("No '"+args[0]+"' attribute on "+obj);
    }

    @objid ("e2d290eb-9978-4cd7-bcac-68a158af487a")
    private static MDependency getMDep(MObject obj, Object[] args) throws IllegalArgumentException {
        MDependency att = obj.getMClass().getDependency((String) args[0]);
        if (att != null)
            return att;
        
        throw new IllegalArgumentException("No '"+args[0]+"' dependency on "+obj);
    }

}
