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

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.SmObjectSmClass;
import org.modelio.vcore.smkernel.mapi.MAttribute;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * MOF SmClass implementation.
 */
@objid ("3aae7df2-9caa-4497-b8fd-5ee2bc1dd087")
public class MofSmClass extends SmObjectSmClass {
    @objid ("adec1a8a-02cb-4060-add3-8bdc735ff95d")
    private boolean isCmsNode;

    @objid ("f342a5a7-cd6a-4287-be98-2c9b88afb3b7")
    private final String name;

    @objid ("0889ae4f-6b1a-4499-aa35-2b64e778ebc2")
    private final String qualifiedName;

    @objid ("6d81bae7-0251-40dc-8986-f490a2f22453")
    private boolean isAbstract;

    @objid ("bb675b84-70af-4081-8ab7-78217592244d")
    private boolean isLinkMetaclass;

    
    @mdl.prop
    @objid ("5a14ceac-abd1-4a97-92ba-e90c54fe2d06")
    public boolean temporary;

    @mdl.propgetter
    public boolean isTemporary() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.temporary;
    }

    @mdl.propsetter
    public void setTemporary(boolean value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.temporary = value;
    }

    @objid ("ab2db04c-ffd1-4870-ae08-c2a5c7909091")
    private SmAttribute nameAtt;

    @objid ("d66c5610-932c-4448-a938-a0f7ed39dbd5")
    private Version version;

    /**
     * @param origin the owner fragment
     * @param name the class name
     * @param qualifiedName the qualified name
     * @param isCmsNode whether the metaclass is a CMS node
     */
    @objid ("2917f2e8-48dc-4765-ba6a-1166d08dfa25")
    public  MofSmClass(ISmMetamodelFragment origin, String name, String qualifiedName, boolean isCmsNode) {
        super(origin);
        this.name = name;
        this.qualifiedName = qualifiedName;
        this.isCmsNode = isCmsNode;
        this.version = new Version(0,0,0);
        
    }

    @objid ("4c09a12d-d849-4432-82b6-5f985bd14955")
    @Override
    public Class<? extends MObject> getJavaInterface() {
        return MMofObject.class;
    }

    @objid ("40dc94eb-0783-46f1-80e3-bc847fac620a")
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Quick access to the 'Name' attribute.
     * @return the 'Name' attribute.
     */
    @objid ("31866cff-7c06-4336-a007-5eaaf62dc026")
    public SmAttribute getNameAtt() {
        return this.nameAtt;
    }

    @objid ("b17cd1c0-65ad-4f76-895a-bf7eab87a76d")
    @Override
    public final String getQualifiedName() {
        return this.qualifiedName;
    }

    @objid ("310e26d3-f29b-4df2-93c5-21c46c92adcd")
    @Override
    public Version getVersion() {
        return this.version;
    }

    @objid ("8eca164a-e9da-4c0d-b273-d74b77d03656")
    @Override
    public boolean isAbstract() {
        return this.isAbstract;
    }

    @objid ("7d6b3562-f189-4954-8666-73f741c927ed")
    @Override
    public boolean isCmsNode() {
        return this.isCmsNode;
    }

    /**
     * A MOF metaclass is NOT a fake metaclass.
     * <p>
     * {@inheritDoc}
     */
    @objid ("248bac1a-89ee-4557-b461-ea0734b1b26d")
    @Override
    public boolean isFake() {
        return false;
    }

    @objid ("f91cded8-7819-4555-b19f-dc20e2fa1b30")
    @Override
    public void load(SmMetamodel m) {
        if (this.parentClass == null) {
            this.parentClass = m.getMClass(SmObjectSmClass.MQNAME);
        }
        
        registerFactory(new MofObjectFactory(this));
        
    }

    @objid ("ba6f9bb9-98b6-4c3f-a5f4-99b19c503e56")
    @Override
    protected void checkNoPostInit() {
        // disable post init checks
    }

    /**
     * Tells whether this metaclass is a relationship metaclass.
     * <p>
     * A relationship metaclass elements represents links between other objects. They have source and target MDependencies.
     * @since toutatis
     * @return true if this metaclass is fake.
     */
    @objid ("3c442a9d-1529-4b5a-b97d-832a9a0652bd")
    @Override
    public boolean isLinkMetaclass() {
        return this.isLinkMetaclass;
    }

    /**
     * @param isAbstract whether the metaclass is abstract.
     */
    @objid ("c8f30401-9cd5-4ba6-aca1-bd7b708d493f")
    public void setAbstract(boolean isAbstract) {
        this.isAbstract = isAbstract;
    }

    /**
     * @param isCmsNode whether the metaclass is a CMS node.
     */
    @objid ("e27791e3-ad12-47d5-8009-8461d0fbe3c5")
    public void setIsCmsNode(boolean isCmsNode) {
        this.isCmsNode = isCmsNode;
    }

    /**
     * @param isLinkMetaclass whether the metaclass is a link metaclass.
     */
    @objid ("6a107fab-9f19-46c0-89aa-b7b3ed152e71")
    public void setLinkMetaclass(boolean isLinkMetaclass) {
        this.isLinkMetaclass = isLinkMetaclass;
    }

    @objid ("daf6b057-d414-48ee-8062-228f45214792")
    @Override
    protected void initCache() {
        Objects.requireNonNull(getObjectFactory(), "object factory not initialized");
        Objects.requireNonNull(getParent(), "parent metaclass not initialized");
        
        super.initCache();
        
        for (MAttribute att : getAttributes(true)) {
            if (att.getName().equalsIgnoreCase("name")) {
                this.nameAtt = (SmAttribute) att;
            }
        }
        
        if(this.nameAtt == null && !this.isAbstract) {
            //Log.warning("%s metaclass has no name attribute.", getQualifiedName());
        }
        
    }

    /**
     * Set the parent metaclass.
     * @param aMofCls the parent metaclass.
     */
    @objid ("572b7ced-7195-47e5-a31d-f874fa51dd40")
    public void setParent(SmClass aMofCls) {
        metaclassModified();
        this.parentClass = aMofCls;
        
    }

    /**
     * @param mofAtt the meta attribute to add.
     */
    @objid ("30096c4a-fad9-4924-9b68-ca192251a64c")
    public void addAttribute(MofSmAttribute mofAtt) {
        assert (! getSelfAttDef().stream().anyMatch( at -> at.getName().equals(mofAtt.getName())));
        
        metaclassModified();
        
        registerAttribute(mofAtt);
        
    }

    /**
     * @param dep the meta dependency to add.
     */
    @objid ("2668e9ab-a632-4f45-9ebc-c4390aaa290b")
    public void addDependency(MofSmDependency dep) {
        assert (! getSelfDepDef().stream().anyMatch( d -> d.getName().equals(dep.getName())))
        : String.format("'%s' dependency already exist in '%s' : %s", dep, this, getSelfDepDef());
        
        metaclassModified();
        
        registerDependency(dep);
        
    }

    @objid ("87769bac-4c24-4897-b84a-92f7be6ac5f7")
    @Override
    public boolean hasBase(MClass other) {
        if (other.equals(this)) {
            return true;
        }
        if (getParent() == null) {
            return false;
        }
        return (getParent().hasBase(other) );
    }

    /**
     * @param version the metaclass version.
     */
    @objid ("e6f75d6b-1a08-4f08-b5b1-4c5343885015")
    public void setVersion(Version version) {
        this.version = version;
    }

    /**
     * Calls {@link #postInit()} if needed.
     */
    @objid ("0796a889-a172-4d41-849d-9ebd0ddb3d68")
    public void ensurePostInit() {
        if (!isPostInitialized()) {
            resetCache();
            postInit();
        }
        
    }

    @objid ("da3357dc-5034-41d6-a9e6-71cbb6a9e78f")
    protected void metaclassModified() {
        resetCache();
        
        getDirectSubClasses().forEach(c -> ((MofSmClass)c).metaclassModified());
        
    }

    @objid ("a23bc355-b516-4489-a116-be48c05bcfbd")
    @Override
    public List<SmDependency> getAllDepDef() {
        ensurePostInit();
        return super.getAllDepDef();
    }

    @objid ("c0c1b625-c94f-4c05-9fc9-48b1a0024cc9")
    @Override
    public List<SmAttribute> getAllAttDef() {
        ensurePostInit();
        return super.getAllAttDef();
    }

    @objid ("782aff83-3655-403b-85bd-83e5a4340330")
    @Override
    public List<SmDependency> getAllComponentAndSharedDepDef() {
        ensurePostInit();
        return super.getAllComponentAndSharedDepDef();
    }

    @objid ("77e61148-4b15-4018-b378-c25d51eb0d64")
    @Override
    public List<SmDependency> getAllComponentDepDef() {
        ensurePostInit();
        return super.getAllComponentDepDef();
    }

    @objid ("2a794abb-4bd0-40d2-a7f5-2152c4919b80")
    @Override
    public List<SmDependency> getAllReferenceDepDef() {
        ensurePostInit();
        return super.getAllReferenceDepDef();
    }

    @objid ("73d584d4-24e3-41f3-9e97-03a2dfdb0afe")
    @Override
    public Collection<SmDependency> getAllSharedCompositionDep() {
        ensurePostInit();
        return super.getAllSharedCompositionDep();
    }

    @objid ("6c35e4ed-fc0d-4956-923b-5abf1f0e431d")
    @Override
    public SmAttribute getAttributeDef(String att_name) {
        ensurePostInit();
        return super.getAttributeDef(att_name);
    }

    @objid ("54d98288-d889-4273-97a5-86246ebf4cd5")
    @Override
    public SmDependency findDependencyDef(String dep_name) {
        ensurePostInit();
        return super.findDependencyDef(dep_name);
    }

    @objid ("33649d08-c3e2-433d-8059-5651ce9e3264")
    @Override
    public String toString() {
        return String.format("%s[%s '%s' v%s ]",
                                                        getClass().getSimpleName(),
                                                        isFake() ? "fake " : "",
                                                        getQualifiedName(),
                                                        getVersion());
        
    }

    /**
     * Delete a dependency.
     * <p>
     * The opposite is not deleted.
     * @param depName the dependency to remove.
     */
    @objid ("81716d51-519c-4b72-86fa-12bd9aedc1a4")
    public void deleteDependency(String depName) {
        SmDependency dep = getDependencyDef(depName);
        if (dep == null) {
            throw new IllegalArgumentException(String.format("%s dependency not in %s: %s", depName, this, getSelfDepDef()));
        }
        removeDependency(dep, true);
        
        metaclassModified();
        ((MofSmClass) dep.getType()).metaclassModified();
        
    }

    @objid ("3ca39900-8881-44fb-b194-a822baccad71")
    private static final class MofObjectFactory implements ISmObjectFactory {
        @objid ("31b26591-3d0c-4ac3-b02b-b5620999d1de")
        MofSmClass mclass;

        @objid ("9ba014cb-f8cd-4dde-bee2-49b296e73847")
        public  MofObjectFactory(MofSmClass mclass) {
            super();
            this.mclass = mclass;
            
        }

        @objid ("795a23d1-1729-4cc8-8c67-c395a0b51d9e")
        @Override
        public SmObjectImpl createImpl() {
            return new MofSmObjectImpl();
        }

        @objid ("345d6a75-aa75-4134-bb8a-68a1d85eff97")
        @Override
        public ISmObjectData createData() {
            return new MofSmObjectData(this.mclass);
        }

    }

}
