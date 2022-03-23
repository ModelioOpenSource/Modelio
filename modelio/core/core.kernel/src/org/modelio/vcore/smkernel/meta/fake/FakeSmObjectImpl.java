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
package org.modelio.vcore.smkernel.meta.fake;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.ISmMeta;
import org.modelio.vcore.smkernel.ISmStorable;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MAttribute;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MVisitor;
import org.modelio.vcore.smkernel.mapi.fake.FakeMObject;
import org.modelio.vcore.smkernel.mapi.fake.FakeMVisitor;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * Fake object implementation.
 */
@objid ("188eb84b-cb6e-474d-8111-b4e01be6bcfd")
public class FakeSmObjectImpl extends SmObjectImpl implements FakeMObject {
    @objid ("aba9113c-ce43-4ba4-a667-7e04c04c5392")
    private static final long serialVersionUID = 1L;

    @objid ("35285d46-0152-4004-84a8-0ea0a9cae0fe")
    @Override
    public Object accept(MVisitor v) {
        if (v instanceof FakeMVisitor) {
            return ((FakeMVisitor)v).visitFakeMObject(this);
        } else {
            return null;
        }
        
    }

    /**
     * Redefined to return a proxy adapter if needed.
     */
    @objid ("a9eade03-c897-4fc2-9b12-d4d036697f8e")
    @SuppressWarnings("unchecked")
    @Override
    public <T extends MObject> T cast(Class<T> cls) {
        if (ProxyObj.directClasses.contains(cls)) {
            return (T) this;
        } else {
            InvocationHandler handler = new ProxyObj(this, getMClass().getMetamodel().getMClass(cls));
        
            return (T) Proxy.newProxyInstance(
                    cls.getClassLoader(),
                    new Class[]{cls, MObject.class, ISmMeta.class, ISmStorable.class},
                    handler);
        }
        
    }

    @objid ("7e969d41-76a2-4940-8cef-a418ea19c14b")
    @Override
    public SmObjectImpl getCompositionOwner() {
        return null;
    }

    @objid ("b2e3eb1c-a998-426b-8404-9083ca74a3c5")
    @Override
    public SmDepVal getCompositionRelation() {
        return null;
    }

    @objid ("ba9603fe-1f3b-447f-bf76-a1f4ea0d2158")
    @Override
    public String getName() {
        return (String) getAttVal(((FakeSmClass)getClassOf()).getNameAtt());
    }

    @objid ("9322b44e-583d-48a4-bb54-768ef4982e1b")
    @Override
    public void setName(String name) {
        setAttVal(((FakeSmClass)getClassOf()).getNameAtt(), name);
    }

    /**
     * Experimental proxy that can adapt a fake object to an existing metamodel interface.
     * <p>
     * All attribute/dependency accessor interface methods are proxied.
     * Calls to {@link #directClasses} methods are forwarded to the fake object.
     * 
     * @author cmarin
     * @since not yet official on 3.4
     * @deprecated not yet official on 3.4
     */
    @objid ("559101ef-c9bc-4574-aca1-c6d3a39f5b7c")
    @Deprecated
    private static class ProxyObj implements InvocationHandler {
        /**
         * Java method whose calls are directly forwarded to the fake object
         */
        @objid ("5d5aa776-9039-42ac-9659-6c16371079a7")
        private static final List<String> stdMet = Arrays.asList("equals", "hashCode", "toString");

        @objid ("a0a172db-7621-4c74-99ce-28d83a4bd179")
        private FakeSmObjectImpl obj;

        @objid ("7d55a1fd-56ab-442b-b1f9-b7c1207bb729")
        private MClass targetClass;

        /**
         * Java classes for which method calls are directly forwarded to the fake object
         */
        @objid ("a87fcc79-c736-4fcb-843c-5ff9190f38bf")
        static final List<Class<? extends Object>> directClasses = Arrays.asList(Object.class, MObject.class, ISmMeta.class, ISmStorable.class);

        @objid ("3a1db8d5-870e-463c-92ec-478c54b0d8ba")
        public  ProxyObj(FakeSmObjectImpl obj, MClass targetClass) {
            this.obj = obj;
            this.targetClass = targetClass;
            
        }

        @objid ("feeb3016-849a-4f6e-ad1d-6ca8f929c18e")
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String metName = method.getName();
            if (metName.equals("accept") && method.getParameterCount() == 1) {
                // Visitor pattern
                String visitMethodName = "visit"+this.targetClass.getName();
                Method m = args[0].getClass().getDeclaredMethod(visitMethodName, this.targetClass.getJavaInterface());
                if (m != null) {
                    return m.invoke(args[0], proxy);
                } else {
                    throw new UnsupportedOperationException(method.toString()+" on "+args[0]+": no "+visitMethodName+" on "+args[0]);
                }
            }
            
            if (directClasses.contains(method.getDeclaringClass()) || stdMet.contains(metName)) {
                return method.invoke(this.obj, args);
            }
            
            FakeSmClass fakeSmClass = (FakeSmClass) this.obj.getClassOf();
            String featureName = metName.substring(3);
            if (metName.startsWith("get")) {
                // Dependency or attribute getter
                MDependency targetDep = this.targetClass.getDependency(featureName);
                if (targetDep != null) {
                    SmDependency dep = fakeSmClass.getDependencyDef(featureName);
                    if (method.getParameterCount() == 1 && method.getParameterTypes()[0] == java.lang.Class.class) {
                        // filtered get accessor
                        return getFilteredAccessor(dep, (Class<?>) args[0]);
                    } else {
                        // non filtered accessor
                        return new SmList<>(this.obj,dep);
                    }
                }
            
                // Look for attribute
                MAttribute targetAtt = this.targetClass.getAttribute(featureName);
                if (targetAtt != null) {
                    SmAttribute smAtt = fakeSmClass.getAttributeDef(featureName);
                    return this.obj.getAttVal(smAtt);
                }
            } else if (metName.startsWith("is")) {
                // Boolean attibute getter
                featureName = metName.substring(2);
                MAttribute targetAtt = this.targetClass.getAttribute(featureName);
                if (targetAtt != null) {
                    SmAttribute smAtt = fakeSmClass.getAttributeDef(featureName);
                    return this.obj.getAttVal(smAtt);
                }
            } else if (metName.startsWith("set")) {
                // Attribute or 0..1 SmDependency setter
                MDependency targetDep = this.targetClass.getDependency(featureName);
                if (targetDep != null) {
                    SmDependency dep = fakeSmClass.findDependencyDef(featureName);
                    if (method.getParameterCount() == 1) {
                        this.obj.appendDepVal(dep, (SmObjectImpl) args[0]);
                        return null;
                    }
                }
            
                // Look for SmAttribute
                MAttribute targetAtt = this.targetClass.getAttribute(featureName);
                if (targetAtt != null) {
                    SmAttribute smAtt = fakeSmClass.findAttributeDef(featureName);
                    this.obj.setAttVal(smAtt, args[0]);
                    return null;
                }
            }
            
            throw new UnsupportedOperationException(method.toString()+" on "+this.obj.toString());
            
        }

        @objid ("88c458a8-85bf-4532-92e3-7d3019ec3b46")
        private Object getFilteredAccessor(SmDependency dep, Class<?> filterClass) {
            if (filterClass == null) {
                throw new IllegalArgumentException();
            }
            final List<Object> results = new ArrayList<>();
            for (final SmObjectImpl element : this.obj.getDepValList(dep)) {
                if (filterClass.isInstance(element)) {
                    results.add(filterClass.cast(element));
                }
            }
            return Collections.unmodifiableList(results);
        }

    }

}
