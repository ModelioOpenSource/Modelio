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

package org.modelio.vcore.smkernel.meta.mof;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
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
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * MOF {@link SmObjectImpl} implementation.
 */
@objid ("a87cf9d6-f70b-4304-9d14-30afa7277054")
public class MofSmObjectImpl extends SmObjectImpl implements MMofObject {
    @objid ("cb2c4b52-9a2a-4f37-9d2f-0a0f9dad85ae")
    private static final long serialVersionUID = 1L;

    @objid ("12ecad4c-565d-4f52-b241-d1c0f2d77cb8")
    @Override
    public Object accept(MVisitor v) {
        throw new UnsupportedOperationException();
    }

    /**
     * Redefined to return a proxy adapter if needed.
     * @deprecated Experimental API, use it at your own risks!
     */
    @objid ("d2eab878-e9bf-486d-89a7-bf1fbf025ea6")
    @SuppressWarnings("unchecked")
    @Override
    @Deprecated
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

    /**
     * Get an attribute value.
     * 
     * @param attName the attribute name.
     * @return attribute value.
     * @throws java.lang.IllegalArgumentException if the attribute does not exist on the metaclass.
     */
    @objid ("36b943bf-fe48-4885-a1df-b6d2d197a872")
    @Override
    public Object getAtt(String attName) throws IllegalArgumentException {
        SmAttribute att = getClassOf().getAttributeDef(attName);
        if (att == null) {
            throw new IllegalArgumentException(String.format(
                    "'%s' attribute not found on %s",
                    attName,
                    this));
        }
        return getAttVal(att);
    }

    @objid ("fd781458-fdd1-4fab-8087-8342fa238c65")
    @Override
    public MofSmClass getClassOf() {
        return (MofSmClass)super.getClassOf();
    }

    @objid ("010b9347-28d1-4957-bd9f-ba6ff21b24ed")
    @Override
    public SmObjectImpl getCompositionOwner() {
        /*for (SmDependency dep : getClassOf().getAllDepDef()) {
            if (dep.isCompositionOpposite()) {
                List<SmObjectImpl> owner = getDepValList(dep);
                if (!owner.isEmpty()) {
                    return owner.get(0);
                }
            }
        }*/
        
        // Avoid one element cycle : exclude owners whose composition owners contain 'this'
        return getCompositionOwners(this)
                                                                        .filter(owner -> getCompositionOwners(owner).noneMatch(this::equals))
                                                                        .findFirst()
                                                                        .orElse(null);
    }

    @objid ("db54e5da-8e85-41a6-8599-76873f58f394")
    @Override
    public SmDepVal getCompositionRelation() {
        for (SmDependency dep : getClassOf().getAllDepDef()) {
            if (dep.isCompositionOpposite()) {
                List<SmObjectImpl> owner = getDepValList(dep);
                if (!owner.isEmpty()) {
                    return new SmDepVal(dep, owner.get(0));
                }
            }
        }
        return null;
    }

    /**
     * Get access to a dependency content.
     * <p>
     * <b>Note:</b> The returned list reflects the content of the dependency at any moment.
     * Modifying the returned list will modify the dependency content.
     * 
     * @param depName the dependency name
     * @return the dependency content.
     */
    @objid ("9a237ffd-20e7-4739-9cf4-f593980a1e02")
    @Override
    public List<MofSmObjectImpl> getDep(String depName) throws IllegalArgumentException {
        MDependency dependency = getClassOf().getDependency(depName);
        if (dependency == null) {
            throw new IllegalArgumentException(String.format("No '%s' dependency in '%s'" , depName, getClassOf()));
        }
        
        // Ugly hard cast List<MObject> --> List<MofSmObjectImpl>
        List<MObject> l1 = mGet(dependency);
        Object o = l1;
        @SuppressWarnings("unchecked")
        List<MofSmObjectImpl> ret = (List<MofSmObjectImpl>) o;
        return ret;
    }

    @objid ("cd057d22-941e-4514-ad68-3763026dc475")
    @Override
    public String getName() {
        SmAttribute nameAtt = getClassOf().getNameAtt();
        if (nameAtt != null) {
            String name = (String) getAttVal(nameAtt);
            if (name != null) {
                return name;
            }
        }
        return "";
    }

    /**
     * Get a 0..1 dependency content.
     * 
     * @param depName the dependency name.
     * @return null or the dependency content.
     * @throws java.lang.IllegalArgumentException if the dependency contains many elements or the dependency does not exist.
     */
    @objid ("075a3dac-16aa-4ec2-adeb-4afe9138fb0b")
    public MofSmObjectImpl getSingleDep(String depName) throws IllegalArgumentException {
        List<MofSmObjectImpl> content = getDep(depName);
        if(content == null || content.isEmpty()) {
            return null;
        } else if (content.size() == 1) {
            return content.get(0);
        } else {
            throw new IllegalArgumentException(String.format("%s.%s contains many elements: %s", this, depName, content));
        }
    }

    /**
     * Set an attribute value
     * 
     * @param attName the attribute name
     * @param value the value to set.
     * @throws java.lang.IllegalArgumentException if the attribute does not exist on the metaclass.
     */
    @objid ("d1108a39-5534-48e6-8744-1182324dc721")
    @Override
    public void setAttVal(String attName, Object value) throws IllegalArgumentException {
        SmAttribute att = getClassOf().getAttributeDef(attName);
        if (att == null) {
            throw new IllegalArgumentException(String.format(
                    "'%s' attribute not found on %s", 
                    attName,
                    this));
        }
        
        setAttVal(att, value);
    }

    @objid ("9ce15fff-9e83-442a-9305-ea23ad96f1da")
    @Override
    public void setName(String name) {
        SmAttribute nameAtt = getClassOf().getNameAtt();
        if (nameAtt != null) {
            setAttVal(nameAtt, name);
        } else {
            throw new UnsupportedOperationException(this+" has no name attribute.");
        }
    }

    @objid ("3c35d3f8-7c4a-45c5-9de7-5bf87af48188")
    private static Stream<SmObjectImpl> getCompositionOwners(SmObjectImpl obj) {
        return obj.getClassOf().getAllDepDef()
                                                                .stream()
                                                                .filter(SmDependency::isCompositionOpposite)
                                                                .flatMap(dep -> obj.getDepValList(dep).stream())
                                                                ;
    }

    /**
     * Experimental proxy that can adapt a MOF object to an existing metamodel interface.
     * <p>
     * All attribute/dependency accessor interface methods are proxied.
     * Calls to {@link #directClasses} methods are forwarded to the fake object.
     * 
     * @author cmarin
     * @since not yet official on 3.4
     * @deprecated not yet official on 3.4
     */
    @objid ("e6f1e297-2be1-4457-8542-d6e6478afac1")
    @Deprecated
    private static class ProxyObj implements InvocationHandler {
        /**
         * Java method whose calls are directly forwarded to the fake object
         */
        @objid ("c955acf0-63fa-4265-9e9f-c2f7abc642f1")
        private static final List<String> stdMet = Arrays.asList("equals", "hashCode", "toString");

        @objid ("d4877347-f871-47f7-b06d-88826b974784")
        private MofSmObjectImpl obj;

        @objid ("29b0197f-139c-4b57-ab5c-3e7227b01303")
        private MClass targetClass;

        /**
         * Java classes for which method calls are directly forwarded to the fake object
         */
        @objid ("49e35319-e499-4a72-a3a9-9dc126a7b8e1")
         static final List<Class<? extends Object>> directClasses = Arrays.asList(Object.class, MObject.class, ISmMeta.class, ISmStorable.class);

        @objid ("324f4f4f-2e09-4615-a84c-d41fe94043fb")
        public ProxyObj(MofSmObjectImpl obj, MClass targetClass) {
            this.obj = obj;
            this.targetClass = targetClass;
        }

        @objid ("8229b330-c557-40f6-8be7-56c689026bb9")
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String metName = method.getName();
            if (metName.equals("accept") && method.getParameterCount() == 1) {
                // Visitor pattern
                String visitMethodName = "visit"+this.targetClass.getName();
                Method m = args[0].getClass().getDeclaredMethod(visitMethodName, this.targetClass.getJavaInterface());
                if (m != null) {
                    m.setAccessible(true);
                    return m.invoke(args[0], proxy);
                } else {
                    throw new UnsupportedOperationException(method.toString()+" on "+args[0]+": no "+visitMethodName+" on "+args[0]);
                }
            }
            
            if (directClasses.contains(method.getDeclaringClass()) || stdMet.contains(metName)) {
                return method.invoke(this.obj, args);
            }
            
            MofSmClass fakeSmClass = this.obj.getClassOf();
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
                // Boolean attribute getter
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
                    SmDependency dep = fakeSmClass.getDependencyDef(featureName);
                    if (dep != null && method.getParameterCount() == 1) {
                        this.obj.appendDepVal(dep, (SmObjectImpl) args[0]);
                        return null;
                    }
                }
            
                // Look for SmAttribute
                MAttribute targetAtt = this.targetClass.getAttribute(featureName);
                if (targetAtt != null) {
                    SmAttribute smAtt = fakeSmClass.getAttributeDef(featureName);
                    if (smAtt != null) {
                        this.obj.setAttVal(smAtt, args[0]);
                        return null;
                    }
                }
            }
            
            throw new UnsupportedOperationException(method.toString()+" on "+this.obj.toString());
        }

        @objid ("ba14ad27-5d25-4f0e-a303-92f522fa20aa")
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
