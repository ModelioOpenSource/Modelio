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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.SmObjectSmClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.fake.FakeMClass;
import org.modelio.vcore.smkernel.mapi.fake.FakeMObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

/**
 * Fake SmClass implementation.
 */
@objid ("86089e3b-e204-4f09-9d5a-9187471054e7")
public class FakeSmClass extends SmObjectSmClass implements FakeMClass {
    @objid ("6ced066c-ce4b-4a11-a434-8650610cae99")
    private boolean isCmsNode;

    @objid ("bf8489d0-7312-4bec-b035-46d6ca101011")
    private final String name;

    @objid ("028c64e4-fc56-4849-bbd7-d616f7fd83e9")
    private final Map<String, SmAttribute> atts = new HashMap<>();

    @objid ("99ec2827-2010-4ca2-9d9c-d04810d35df8")
    private final Map<String, SmDependency> deps = new HashMap<>();

    @objid ("abb258da-efb3-490b-8d8d-794e49221b15")
    private SmAttribute nameAtt;

    /**
     * @param origin the owner fragment
     * @param name the class name
     * @param isCmsNode whether the metaclass is a CMS node
     */
    @objid ("8a4b0292-61ad-4355-ba3a-204e533d5880")
    public  FakeSmClass(ISmMetamodelFragment origin, String name, boolean isCmsNode) {
        super(origin);
        this.name = name;
        this.isCmsNode = isCmsNode;
        
    }

    @objid ("f74604ed-4aca-47a1-9b6d-206c92e048f8")
    public void addDependency(FakeSmDependency ret) {
        this.deps.put(ret.getName(), ret);
        registerDependency(ret);
        
        resetCache();
        initCache();
        
    }

    /**
     * Get the attribute definition with the given name.
     * <p>
     * Look into the class attributes and inherited attributes.
     * @param att_name the meta attribute name
     * @return the found meta attribute or null.
     */
    @objid ("91ae9bb0-196d-4897-bdaf-86e282f59ef7")
    public SmAttribute findAttributeDef(String att_name) {
        SmAttribute ret = super.getAttributeDef(att_name);
        
        if (ret == null) {
            ret = this.atts.get(att_name);
        }
        return ret;
    }

    /**
     * Get the relation definition with the given name.
     * <p>
     * Look into the class relations and inherited relations
     * @param dep_name the meta relation name
     * @return the found meta relation or null.
     */
    @objid ("72ebb660-b387-4251-817a-335b783d55d7")
    @Override
    public SmDependency findDependencyDef(String dep_name) {
        SmDependency ret = super.findDependencyDef(dep_name);
        if (ret == null) {
            ret = this.deps.get(dep_name);
        }
        return ret;
    }

    /**
     * Redefined to create the meta attribute if not found.
     */
    @objid ("fcf96598-3328-40b5-88b5-a5b7eb1be13d")
    @Override
    public SmAttribute getAttributeDef(String att_name) {
        SmAttribute ret = findAttributeDef(att_name);
        
        if (ret == null) {
            ret = new FakeSmAttribute(this, att_name);
            registerAttribute(ret);
            this.atts.put(att_name, ret);
        }
        return ret;
    }

    /**
     * Redefined to create the meta attribute if not found.
     */
    @objid ("f2275026-4d54-4401-b93f-3ccee5019e4c")
    @Override
    public SmDependency getDependencyDef(String dep_name) {
        SmDependency ret = findDependencyDef(dep_name);
        if (ret != null) {
            return ret;
        }
        
        FakeSmDependency fakeDep = new FakeSmDependency(this, dep_name);
        addDependency(fakeDep);
        return fakeDep;
    }

    @objid ("3fdefa5f-8129-41cc-b31c-04072f4223d4")
    @Override
    public Class<? extends MObject> getJavaInterface() {
        return FakeMObject.class;
    }

    @objid ("acb78b3d-f4ee-4d67-923f-c3e916ae0a73")
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Get the "Name" attribute defined for all fake metaclasses.
     * @return the "Name" attribute.
     */
    @objid ("a728cf97-400e-4d12-b78b-596bb0e06b89")
    public SmAttribute getNameAtt() {
        return this.nameAtt;
    }

    /**
     * Get or create a fake dependency from a dependency that existed on the metaclass before it was discarded.
     * <p>
     * @param orig a dependency that existed on the metaclass before it becomes fake.
     * @return the matching fake dependency.
     */
    @objid ("9ba6af45-5bd4-4a39-8dd9-87e1bf5e88bb")
    @Override
    public MDependency getSameDependency(MDependency orig) {
        FakeSmDependency ret = (FakeSmDependency) this.deps.get(orig.getName());
        
        if (ret == null) {
            ret = new FakeSmDependency(this, (SmDependency) orig);
            addDependency(ret);
        }
        return ret;
    }

    @objid ("6f91234f-a50d-40ce-b95f-9e4c27a19765")
    @Override
    public Version getVersion() {
        return new Version(0,0,0);
    }

    @objid ("acc393b0-e426-4ef0-bf4c-4eb0d2ddd7a6")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("eb1e16be-fdbf-4a74-be4f-913daca70286")
    @Override
    public boolean isCmsNode() {
        return this.isCmsNode;
    }

    @objid ("96d1141c-6edf-4c60-bb6c-04ca6b9bdffe")
    @Override
    public boolean isFake() {
        return true;
    }

    /**
     * Tells whether this metaclass is a relationship metaclass.
     * <p>
     * A relationship metaclass elements represents links between other objects. They have source and target MDependencies.
     * @since toutatis
     * @return true if this metaclass is fake.
     */
    @objid ("c263441a-166a-4f5f-aec9-5c76c761823e")
    @Override
    public boolean isLinkMetaclass() {
        return false;
    }

    @objid ("b28222e5-deae-41c6-b1ba-891e70774917")
    @Override
    public void load(SmMetamodel m) {
        this.parentClass = m.getMClass(SmObjectSmClass.MQNAME);
        
        registerFactory(new ISmObjectFactory() {
        
            @Override
            public SmObjectImpl createImpl() {
                return new FakeSmObjectImpl();
            }
        
            @Override
            public ISmObjectData createData() {
                return new FakeSmObjectData(FakeSmClass.this);
            }
        });
        
        // register attributes
        
        this.nameAtt = new NameAtt();
        this.nameAtt.init("Name", this, String.class);
        
        this.atts.put("Name", this.nameAtt);
        registerAttribute(this.nameAtt);
        
    }

    /**
     * @param parentMetaclass the parent metaclass
     */
    @objid ("3f44a1f0-fd43-4876-8ed4-48f4ec412dcb")
    public void setParent(SmClass parentMetaclass) {
        SmClass oldParent = this.parentClass;
        
        this.parentClass = parentMetaclass;
        
        if (isPostInitialized()) {
            // reset parent cache oldParent parent.getSub() contains this.
            oldParent.getCacheAccess().remake();
            
            // remake own cache
            getCacheAccess().remake();
            
            // remake subclasses cache
            getAllSubClasses().forEach(sub -> {
                sub.getCacheAccess().remake();
            });
        
        }
        
    }

    @objid ("a03d12fa-6955-48a5-8a6f-00db77aba3b8")
    @Override
    protected void checkNoPostInit() {
        // disable post init checks
    }

    @objid ("253bf498-2f95-4ba2-bb0b-2b121869bb4c")
    @Override
    protected void initCache() {
        assert (this.allAttributes.isEmpty()) : this.allAttributes.toString();
        
        // initialize flatten attributes and dependencies
        this.allAttributes.addAll(this.selfAttributes);
        this.allDependencies.addAll(this.selfDependencies);
        this.allComponentDep.addAll(this.selfComponentDep);
        this.allReferenceDep.addAll(this.selfReferenceDep);
        this.allSharedDep.addAll(this.selfSharedDep);
        this.allComponentAndSharedDep.addAll(this.selfComponentAndSharedDep);
        
        // flatten attributes and dependencies
        SmClass parent = getParent();
        while (parent != null) {
            if (parent.isFake()) {
                this.allAttributes.addAll(parent.getSelfAttDef());
                this.allDependencies.addAll(parent.getSelfDepDef());
                for (SmDependency pdep : parent.getSelfDepDef()) {
                    if (pdep.isComponent()) {
                        this.allComponentDep.add(pdep);
                        this.allComponentAndSharedDep.add(pdep);
                    }
                    if (pdep.isSharedComposition()) {
                        this.allSharedDep.add(pdep);
                        this.allComponentAndSharedDep.add(pdep);
                    }
                    if (pdep.isPartOf()) {
                        this.allSharedDep.add(pdep);
                    }
                }
            } else {
                // Convert SmAttributes to FakeSmAttribute and SmDependency to FakeSmDependency.
                for (SmAttribute patt : parent.getSelfAttDef()) {
                    this.allAttributes.add(new FakeSmAttribute(this, patt));
                }
                for (SmDependency pdep : parent.getSelfDepDef()) {
                    FakeSmDependency dcopy = new FakeSmDependency(this, pdep);
                    this.allDependencies.add(dcopy);
                    if (pdep.isComponent()) {
                        this.allComponentDep.add(pdep);
                        this.allComponentAndSharedDep.add(pdep);
                    }
                    if (pdep.isSharedComposition()) {
                        this.allSharedDep.add(pdep);
                        this.allComponentAndSharedDep.add(pdep);
                    }
                    if (pdep.isPartOf()) {
                        this.allSharedDep.add(pdep);
                    }
                }
            }
        
            parent = parent.getParent();
        }
        
        ((ArrayList<?>) this.allAttributes).trimToSize();
        ((ArrayList<?>) this.allDependencies).trimToSize();
        ((ArrayList<?>) this.allComponentDep).trimToSize();
        ((ArrayList<?>) this.allSharedDep).trimToSize();
        ((ArrayList<?>) this.allReferenceDep).trimToSize();
        ((ArrayList<?>) this.allComponentAndSharedDep).trimToSize();
        
        this.allLinkSourceDeps = this.allDependencies
                .stream()
                .filter(dep -> dep.hasDirective(SmDirective.SMCDLINKSOURCE))
                .collect(Collectors.<MDependency>toList());
        
        this.allLinkTargetDeps = this.allDependencies
                .stream()
                .filter(dep -> dep.hasDirective(SmDirective.SMCDLINKTARGET))
                .collect(Collectors.<MDependency>toList());
        
    }

    /**
     * The Name attribute.
     */
    @objid ("89cac9c9-e25e-4888-80b5-6c9e9f206d2d")
    public static class NameAtt extends SmAttribute {
        @objid ("56ca5d69-82dd-4546-afd0-165913cd9086")
        @Override
        public Object getValue(ISmObjectData object) {
            return ((FakeSmObjectData)object).name;
        }

        @objid ("2c764d22-5464-47cc-8fb1-080691975dc9")
        @Override
        public void setValue(ISmObjectData object, Object value) {
            ((FakeSmObjectData)object).name = (String) value;
        }

    }

}
