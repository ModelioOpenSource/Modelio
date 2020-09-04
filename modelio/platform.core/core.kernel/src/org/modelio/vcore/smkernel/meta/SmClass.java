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

package org.modelio.vcore.smkernel.meta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.ecore.EClass;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.mapi.MAttribute;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

/**
 * A <code>SmClass</code> describes a semantic type, ie a metamodel metaclass.
 * <p>
 * Objects managed by the kernel (ie SmObject) are typed by their SmClass associated instance. Therefore, all SmObject instances
 * sharing the same SmClass instance are said to belong to the metaclass defined by SmClass. There is a unique instance of SmClass
 * per meta-class.
 * <p>
 * SmClass instances are build at run-time from the Java class that defines their meta-class. The Java reflection API along with
 * Java annotations are used to find out what are the SmAttribute and SmDependency instances to declare on the SmClass instance.
 * 
 * @author phv
 */
@objid ("008435a0-ed97-1f1f-85a5-001ec947cd2a")
public abstract class SmClass extends SmElement implements MClass {
    /**
     * Hashcode cached value.
     */
    @objid ("b51291e5-6200-4c32-903d-ea001fa1f82c")
    private int hashCode;

    /**
     * The integer-based identifier of the metaclass.
     */
    @objid ("0083ed70-ed97-1f1f-85a5-001ec947cd2a")
    private short metaclassId;

    /**
     * Internal flag used as a state indicator by  initialization process
     */
    @objid ("6ff5136d-1d84-4b2a-9d88-7946ec7aee8b")
    private boolean postInitialized;

    /**
     * Internal state flag.
     */
    @objid ("55ab9eb6-28ce-4215-a1af-e224dee7f603")
    private boolean enabled = true;

    @objid ("006ca804-4412-1f74-8a7a-001ec947cd2a")
    protected final List<SmAttribute> allAttributes = new ArrayList<>();

    /**
     * Self and inherited composition and shared composition dependencies.
     */
    @objid ("ef7c6080-bea9-11e1-b576-001ec947ccaf")
    protected final List<SmDependency> allComponentAndSharedDep = new ArrayList<>();

    /**
     * Self and inherited composition dependencies.
     */
    @objid ("005e8648-4939-1f74-8a7a-001ec947cd2a")
    protected final List<SmDependency> allComponentDep = new ArrayList<>();

    @objid ("00548684-45d1-1f74-8a7a-001ec947cd2a")
    protected final List<SmDependency> allDependencies = new ArrayList<>();

    /**
     * Self and inherited reference dependencies.
     */
    @objid ("005ec806-4939-1f74-8a7a-001ec947cd2a")
    protected final List<SmDependency> allReferenceDep = new ArrayList<>();

    /**
     * Self and inherited shared composition dependencies.
     */
    @objid ("f2e7fb12-bfa2-11e1-b511-001ec947ccaf")
    protected final List<SmDependency> allSharedDep = new ArrayList<>();

    @objid ("784b616b-b708-4154-8e14-6ebcadd072e8")
    private SmMetamodel metamodel;

    @objid ("005144b0-fd1a-1f27-a7da-001ec947cd2a")
    private ISmObjectFactory objectFactory;

    /**
     * The metamodel fragment providing the meta class
     */
    @objid ("555abb1a-a45d-4b8c-9e83-f112bbda1cb0")
    private final ISmMetamodelFragment origin;

    /**
     * The parent SmClass of this SmClass.
     * <p>
     * The inheritance relation between SmClass instances reproduces the inheritance relation between metaclasses.
     */
    @objid ("008404ea-ed97-1f1f-85a5-001ec947cd2a")
    protected SmClass parentClass;

    /**
     * The SmAttribute instances representing the meta-attributes of the metaclass.
     */
    @objid ("00840026-ed97-1f1f-85a5-001ec947cd2a")
    protected final List<SmAttribute> selfAttributes = new ArrayList<>();

    /**
     * Composition and shared composition dependencies.
     */
    @objid ("ef7c607b-bea9-11e1-b576-001ec947ccaf")
    protected final List<SmDependency> selfComponentAndSharedDep = new ArrayList<>();

    @objid ("0084017a-ed97-1f1f-85a5-001ec947cd2a")
    protected final List<SmDependency> selfComponentDep = new ArrayList<>();

    @objid ("00840288-ed97-1f1f-85a5-001ec947cd2a")
    protected final List<SmDependency> selfDependencies = new ArrayList<>();

    /**
     * Self reference dependencies.
     */
    @objid ("00840710-ed97-1f1f-85a5-001ec947cd2a")
    protected final List<SmDependency> selfReferenceDep = new ArrayList<>();

    /**
     * Shared composition dependencies.
     */
    @objid ("f2e7fb0e-bfa2-11e1-b511-001ec947ccaf")
    protected final List<SmDependency> selfSharedDep = new ArrayList<>();

    /**
     * The children SmClass of this SmClass.
     * <p>
     * The inheritance relation between SmClass instances reproduces the inheritance relation between metaclasses.
     */
    @objid ("00840814-ed97-1f1f-85a5-001ec947cd2a")
    private final List<SmClass> subClasses = new ArrayList<>();

    /**
     * The matching EMF class.
     */
    @objid ("1f1f4548-6e07-4752-9738-2d09ad5b0e4e")
    private EClass emfAdapter;

    @objid ("35e5d445-2f05-462a-9288-e926a91e1948")
    protected Collection<MDependency> allLinkSourceDeps;

    @objid ("d00868dd-8fcf-409c-b6f9-3a018bd7732f")
    protected Collection<MDependency> allLinkTargetDeps;

    /**
     * <s>Two <code>SmClass</code> are the same if they have the same name and their metamodel fragment are
     * {@link ISmMetamodelFragment#equals(Object) equal}.</s>
     * <p>
     * Since Modelio 3.4, Test for a strict equality of instances.
     */
    @objid ("86233e2f-7d84-11e1-bee3-001ec947ccaf")
    @Override
    public boolean equals(final Object obj) {
        return this == obj;
    }

    /**
     * The 'extEquals' stands for "extended equals".
     * <p>
     * If both <i>this</i> and <i>other</i> SmClass belong to the same MM Fragment they are simply compared for strict equality.<br/>
     * If <i>this</i> and <i>other</i> SmClass belong to different fragments the returned value is equivalent to
     * <i>this.hasBase(other)</i>; ie this inherits from other.
     * <p>
     * The typical usage of 'extEquals' is to compare metaclasses in checkers or audit rules while taking into account the extension
     * metamodel fragments and their resulting extended inheritance tree!
     * 
     * @param other another metaclass
     * @return true if this class is the same as the other, or is a sub class of the other in another fragment.
     */
    @objid ("3e6f6405-4e05-4a63-a127-2c3f8d894b0a")
    public boolean extEquals(SmClass other) {
        if (getOrigin() == other.getOrigin()) {
            return (equals(other));
        } else {
            return hasBase(other);
        }
    }

    /**
     * Get self and inherited attribute definitions.
     * <p>
     * 
     * @return attribute definitions.
     */
    @objid ("0083ee2e-ed97-1f1f-85a5-001ec947cd2a")
    public List<SmAttribute> getAllAttDef() {
        return this.allAttributes;
    }

    /**
     * Get self and inherited composition and shared composition dependencies.
     * <p>
     * 
     * @return composition and shared composition dependencies.
     */
    @objid ("ef85e9e0-bea9-11e1-b576-001ec947ccaf")
    public List<SmDependency> getAllComponentAndSharedDepDef() {
        return this.allComponentAndSharedDep;
    }

    /**
     * Get self and inherited composition dependencies.
     * <p>
     * Excludes shared compositions.
     * 
     * @return composition dependencies.
     */
    @objid ("0083eec4-ed97-1f1f-85a5-001ec947cd2a")
    public List<SmDependency> getAllComponentDepDef() {
        return this.allComponentDep;
    }

    /**
     * Get all defined dependencies.
     * 
     * @return all dependencies.
     */
    @objid ("0083ef82-ed97-1f1f-85a5-001ec947cd2a")
    public List<SmDependency> getAllDepDef() {
        return this.allDependencies;
    }

    /**
     * Get self and inherited reference dependencies.
     * <p>
     * Excludes compositions, shared compositions, and "non navigable" dependencies..
     * 
     * @return composition dependencies.
     */
    @objid ("0083f0b8-ed97-1f1f-85a5-001ec947cd2a")
    public List<SmDependency> getAllReferenceDepDef() {
        return this.allReferenceDep;
    }

    /**
     * Get self and inherited shared composition dependencies.
     * 
     * @return self and inherited shared composition dependencies.
     */
    @objid ("f2e7fb1e-bfa2-11e1-b511-001ec947ccaf")
    public Collection<SmDependency> getAllSharedCompositionDep() {
        return this.allSharedDep;
    }

    /**
     * Get all the sub classes recursively.
     * 
     * @return all the sub classes.
     */
    @objid ("0083f1e4-ed97-1f1f-85a5-001ec947cd2a")
    public final List<SmClass> getAllSubClasses() {
        final List<SmClass> results = new ArrayList<>();
        
        // Add direct sub classes
        results.addAll(this.subClasses);
        
        for (final SmClass c : this.subClasses) {
            results.addAll(c.getAllSubClasses());
        }
        return results;
    }

    @objid ("00034a76-4c5f-1ffc-8433-001ec947cd2a")
    @Override
    public final MAttribute getAttribute(final String name) {
        return getAttributeDef(name);
    }

    /**
     * Get the attribute definition with the given name.
     * <p>
     * Look into the class attributes and inherited attributes.
     * 
     * @param att_name the attribute name
     * @return the found attribute or <code>null</code> if none has the given name.
     */
    @objid ("0083f27a-ed97-1f1f-85a5-001ec947cd2a")
    public SmAttribute getAttributeDef(final String att_name) {
        for (final SmAttribute att : this.allAttributes) {
            if (att.getName().equals(att_name)) {
                return att;
            }
        }
        return null;
    }

    @objid ("000420e0-4c5f-1ffc-8433-001ec947cd2a")
    @Override
    public final List<MAttribute> getAttributes(boolean includeInherited) {
        if (includeInherited) {
            return Collections.unmodifiableList(getAllAttDef());
        } else {
            return Collections.unmodifiableList(getSelfAttDef());
        }
    }

    @objid ("0003d806-4c5f-1ffc-8433-001ec947cd2a")
    @Override
    public final List<MDependency> getDependencies(boolean includeInherited) {
        if (includeInherited) {
            return Collections.unmodifiableList(getAllDepDef());
        } else {
            return Collections.unmodifiableList(getSelfDepDef());
        }
    }

    @objid ("000391d4-4c5f-1ffc-8433-001ec947cd2a")
    @Override
    public final MDependency getDependency(final String name) {
        return getDependencyDef(name);
    }

    /**
     * Get the dependency definition with the given name.
     * <p>
     * Look into the class dependency and inherited dependency.
     * <p>
     * Some implementations may choose to create a SmDependency if none exist.
     * 
     * @param dep_name the dependency name
     * @return the found dependency or <code>null</code> if none has the given name.
     */
    @objid ("0083f3a6-ed97-1f1f-85a5-001ec947cd2a")
    public SmDependency getDependencyDef(final String dep_name) {
        return findDependencyDef(dep_name);
    }

    /**
     * Get the EMF adapter for this class.
     * 
     * @return the EMF class.
     */
    @objid ("ef9437fd-bea9-11e1-b576-001ec947ccaf")
    public EClass getEmfAdapter() {
        return this.emfAdapter;
    }

    /**
     * Get the metaclass id.
     * <p>
     * This identifier must not be used in long term storage as may change at each metamodel change.
     * 
     * @return the metaclass id.
     */
    @objid ("0056e398-fd1a-1f27-a7da-001ec947cd2a")
    public final short getId() {
        return this.metaclassId;
    }

    @objid ("4fd793dd-3830-48de-8a76-7b27689d7fa8")
    @Override
    public MMetamodel getMetamodel() {
        return this.metamodel;
    }

    /**
     * Get the factory to use to instantiate model objects of this class.
     * 
     * @return the model object factory.
     */
    @objid ("0054a61e-fd1a-1f27-a7da-001ec947cd2a")
    public ISmObjectFactory getObjectFactory() {
        return this.objectFactory;
    }

    /**
     * Get the metamodel fragment owning this metaclass.
     * 
     * @return the metamodel fragment.
     */
    @objid ("8dda2d7a-5750-4d6c-b375-b3c525533a9c")
    @Override
    public ISmMetamodelFragment getOrigin() {
        // Automatically generated method. Please do not modify this code.
        return this.origin;
    }

    /**
     * Get the parent class.
     * 
     * @return the parent class.
     */
    @objid ("00840936-ed97-1f1f-85a5-001ec947cd2a")
    public SmClass getParent() {
        return this.parentClass;
    }

    @objid ("1669a3a2-6951-4641-8845-b913afe0bbb1")
    @Override
    public String getQualifiedName() {
        return getOrigin().getName() + QUALIFIER_SEP + getName();
    }

    /**
     * Get the attributes defined on this class excluding inherited attributes.
     * 
     * @return the class attribute definitions.
     */
    @objid ("0083f5f4-ed97-1f1f-85a5-001ec947cd2a")
    public List<SmAttribute> getSelfAttDef() {
        return this.selfAttributes;
    }

    /**
     * Get the metamodel dependencies defined on this class.
     * 
     * @return the metamodel dependencies.
     */
    @objid ("0083f680-ed97-1f1f-85a5-001ec947cd2a")
    public List<SmDependency> getSelfDepDef() {
        return this.selfDependencies;
    }

    @objid ("0004a236-4c5f-1ffc-8433-001ec947cd2a")
    @Override
    public List<MClass> getSub(boolean recursive) {
        if (recursive) {
            return Collections.unmodifiableList(getAllSubClasses());
        } else {
            return Collections.unmodifiableList(this.subClasses);
        }
    }

    @objid ("00046a1e-4c5f-1ffc-8433-001ec947cd2a")
    @Override
    public MClass getSuper() {
        return this.parentClass;
    }

    /**
     * @param parent a metamodel class
     * @return <code>true</code> if <code>this</code> class inherits from the given class. <code>false</code> otherwise.
     */
    @objid ("0083f7ac-ed97-1f1f-85a5-001ec947cd2a")
    @Override
    public boolean hasBase(MClass parent) {
        if (parent == null) {
            return false;
        } else {
            return parent.getJavaInterface().isAssignableFrom(getJavaInterface());
        }
        /*
         * SmClass cls = this; while (cls != null && !cls.equals(parent)) { cls = cls.parentClass; } return cls == parent;
         */
    }

    /**
     * Tells whether this class or an inherited class has the given {@link SmDirective}.
     * 
     * @param flag a flag to test
     * @return <code>true</code> if the class has the flag, else <code>false</code>
     */
    @objid ("0083f8ce-ed97-1f1f-85a5-001ec947cd2a")
    public boolean hasDirectiveInGraph(final SmDirective flag) {
        SmClass c = this;
        while (c != null && !c.hasDirective(flag)) {
            c = c.getParent();
        }
        return c != null;
    }

    @objid ("86233e2a-7d84-11e1-bee3-001ec947ccaf")
    @Override
    public int hashCode() {
        if (this.hashCode == 0) {
            // Compute hash code now
            final int prime = 31;
            int result = 1;
        
            String lname = getName();
            ISmMetamodelFragment lorigin = getOrigin();
        
            result = prime * result + ((lname == null) ? 0 : lname.hashCode());
            result = prime * result + ((lorigin == null) ? 0 : lorigin.hashCode());
            this.hashCode = result;
        }
        return this.hashCode;
    }

    @objid ("c966f3dc-21c9-4969-878b-331bbd8127e1")
    @Override
    public boolean isFake() {
        return false;
    }

    /**
     * Load the metaclass content.
     * <p>
     * Note implementers should <b>never</b> call super implementation.
     * 
     * @param m the metamodel asking for loading.
     */
    @objid ("0083fbda-ed97-1f1f-85a5-001ec947cd2a")
    public abstract void load(SmMetamodel m);

    /**
     * Finish the class initialization.
     * <p>
     * This method must be called once.
     */
    @objid ("00715c6e-4412-1f74-8a7a-001ec947cd2a")
    public void postInit() {
        checkNoPostInit();
        
        resetCache();
        this.postInitialized = true;
        
        SmClass parent = getParent();
        
        // register itself in parent subclasses
        if (parent != null && !parent.subClasses.contains(this)) {
            parent.subClasses.add(this);
        }
        
        initCache();
    }

    /**
     * Initialize the EMF EClass adapter.
     * 
     * @param emfAdapter the EMS EClass adapter
     */
    @objid ("ef943802-bea9-11e1-b576-001ec947ccaf")
    public void setEmfAdapter(EClass emfAdapter) {
        this.emfAdapter = emfAdapter;
    }

    @objid ("00031448-eb1c-1f22-8c06-001ec947cd2a")
    @Override
    public String toString() {
        return getQualifiedName() + " "+getClass().getSimpleName();
    }

    /**
     * Note: package visibility, method to used only locally
     * @param id
     */
    @objid ("ec5ce1d3-b0aa-4486-bda6-68eed1ad47d9")
    void setMetaclassId(short id) {
        this.metaclassId = id;
    }

    /**
     * Initialize the metamodel.
     * <p>
     * This method must be called once for each SmClass.
     * 
     * @param metamodel the metamodel.
     */
    @objid ("ab0d29a0-d4f1-4346-bde6-8e413dea6f38")
    void setMetamodel(SmMetamodel metamodel) {
        assert (this.metamodel == null);
        
        this.metamodel = metamodel;
    }

    /**
     * Package visibility C'tor (usage reserved for Metamodel class)
     * @param interf
     * @param name
     * @param mmFragment
     */
    @objid ("0083fb12-ed97-1f1f-85a5-001ec947cd2a")
    protected SmClass(ISmMetamodelFragment origin) {
        this.origin = origin;
    }

    /**
     * Register an attribute.
     * 
     * @param att an attribute
     */
    @objid ("232293ad-b284-4e0f-b537-97a2325d66d7")
    protected final void registerAttribute(SmAttribute att) {
        checkNoPostInit();
        
        this.selfAttributes.add(att);
    }

    /**
     * Register a dependency.
     * 
     * @param dep a dependency.
     */
    @objid ("cbb63344-16ef-4d9c-b6e5-dfc1b729e28b")
    protected final void registerDependency(SmDependency dep) {
        checkNoPostInit();
        
        assert (! this.selfDependencies.stream().filter(d -> d.getName().equals(dep.getName())).findAny().isPresent()) : this.selfDependencies.toString();
        
        this.selfDependencies.add(dep);
        
        if (dep.isComponent()) {
            this.selfComponentDep.add(dep);
            this.selfComponentAndSharedDep.add(dep);
        } else if (dep.isSharedComposition()) {
            this.selfComponentAndSharedDep.add(dep);
            this.selfSharedDep.add(dep);
        } else if (dep.isPartOf()) {
            this.selfReferenceDep.add(dep);
        }
    }

    /**
     * Initialize the object factory.
     * <p>
     * Must be called once for each SmClass.
     * 
     * @param iSmObjectFactory the object factory.
     */
    @objid ("d0e6502b-d210-4983-9def-37b5b4167233")
    protected final void registerFactory(ISmObjectFactory iSmObjectFactory) {
        if (this.objectFactory != null) {
            throw new IllegalStateException(this + " object factory already initialized to " + this.objectFactory);
        }
        
        this.objectFactory = iSmObjectFactory;
    }

    /**
     * Asserts that {@link #postInit()} has not yet been called.
     * 
     * @throws java.lang.IllegalStateException if {@link #postInit()} has already been called.
     */
    @objid ("b6a848da-03a0-468e-832c-17636715421f")
    protected void checkNoPostInit() throws IllegalStateException {
        if (isPostInitialized()) {
            throw new IllegalStateException("postInit() already called on " + this);
        }
    }

    /**
     * Initialize the internal cache.
     * <p>
     * <b>Do not mess with this method.</b>
     */
    @objid ("d61e55b3-d003-479c-a4c0-20851b4bcb38")
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
            this.allAttributes.addAll(parent.selfAttributes);
        
            this.allDependencies.addAll(parent.selfDependencies);
            this.allComponentDep.addAll(parent.selfComponentDep);
            this.allSharedDep.addAll(parent.selfSharedDep);
            this.allReferenceDep.addAll(parent.selfReferenceDep);
            this.allComponentAndSharedDep.addAll(parent.selfComponentAndSharedDep);
        
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
     * Tells whether this metaclass is a relationship metaclass.
     * <p>
     * A relationship metaclass elements represents links between other objects. They have source and target MDependencies.
     * @since toutatis
     * 
     * @return true if this metaclass is fake.
     */
    @objid ("8bfce0f9-9e32-4fb4-9cb1-3d5497f57ad9")
    @Override
    public boolean isLinkMetaclass() {
        return false;
    }

    /**
     * Tells whether this metaclass may have orphan model objects.
     * @since 3.6
     * 
     * @return true if orphan are allowed.
     */
    @objid ("0b74a4f7-c317-45cf-b0ac-9ed800332ee7")
    @Override
    public boolean areOrphansAllowed() {
        return false;
    }

    /**
     * Get for a link metaclass all target dependencies.
     * <p>
     * Also return inherited target dependencies.
     * Returns an empty collection for node metaclasses.
     * 
     * @return all target dependencies.
     */
    @objid ("20ff16f4-c869-40ce-9530-9bdc4e84e320")
    @Override
    public Collection<MDependency> getLinkMetaclassTargets() {
        return this.allLinkTargetDeps;
    }

    /**
     * Get for a link metaclass all source dependencies.
     * <p>
     * Also return inherited source dependencies.
     * Returns an empty collection for node metaclasses.
     * 
     * @return all source dependencies.
     */
    @objid ("b79f3f8e-683b-4383-aea6-4cc5ab3c5ca3")
    @Override
    public Collection<MDependency> getLinkMetaclassSources() {
        return this.allLinkSourceDeps;
    }

    @objid ("2e08c595-8331-4301-a511-32bef3e667de")
    protected final boolean isPostInitialized() {
        return (this.postInitialized);
    }

    /**
     * Reset the internal cache.
     * <p>
     * <b>Do not mess with this method.</b>
     */
    @objid ("46c65de3-bed5-4594-9916-43ea2c54b9bd")
    protected void resetCache() {
        if (isPostInitialized() || ! this.allAttributes.isEmpty()) {
            this.postInitialized = false;
            
            this.allAttributes.clear();
            this.allDependencies.clear();
            this.allComponentDep.clear();
            this.allReferenceDep.clear();
            this.allSharedDep.clear();
            this.allComponentAndSharedDep.clear();
        }
    }

    @objid ("9f44b918-1a96-41cf-bfad-dbfda959bb8c")
    protected final List<SmClass> getDirectSubClasses() {
        return this.subClasses;
    }

    @objid ("89f91b27-a81a-42ae-9e36-d09a4dcf98a6")
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @objid ("2e442c51-e67c-491c-862f-7c1ffe1e4b63")
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    /**
     * Look for the dependency definition with the given name.
     * <p>
     * Look into the class dependency and inherited dependency.
     * 
     * @param dep_name the dependency name
     * @return the found dependency or <code>null</code> if none has the given name.
     * @since 3.7
     */
    @objid ("4e37eb62-0731-4f7e-994c-70efc4e62459")
    public SmDependency findDependencyDef(String dep_name) {
        for (final SmDependency dep : this.allDependencies) {
            if (dep.getName().equals(dep_name)) {
                return dep;
            }
        }
        return null;
    }

    /**
     * Get an access to the internal cache.
     * <p>
     * Do not mess with it.
     * @author cma
     * @since 3.7
     * 
     * @return the internal cache access.
     */
    @objid ("21229e80-1538-4afe-8c1d-9a636ae766a0")
    public ICacheAccess getCacheAccess() {
        return new ICacheAccess() {
                                    @Override
                                    public void reset() {
                                        resetCache();
                                    }
                                    
                                    @Override
                                    public void init() {
                                        initCache();
                                    }
                        
                                    @Override
                                    public void remake() {
                                        resetCache();
                                        initCache();
                                    }
                                };
    }

    /**
     * Remove a SmDependency.
     * <p>
     * <b>Do not mess with this method.</b>
     * 
     * @param dep the dependency to remove
     * @param withOpposite whether to delete the oppostie too.
     */
    @objid ("be321efd-12bf-4c58-b8b9-8637a8ad9046")
    protected void removeDependency(SmDependency dep, boolean withOpposite) {
        assert ( getSelfDepDef().contains(dep)) : String.format("'%s.%s' is not in '%s' : %s", dep.getSource(), dep, this, getSelfDepDef());
        
        this.selfDependencies.remove(dep);
        this.selfComponentDep.remove(dep);
        this.selfComponentAndSharedDep.remove(dep);
        this.selfSharedDep.remove(dep);
        this.selfReferenceDep.remove(dep);
        
        this.allDependencies.remove(dep);
        /*
        this.allLinkSourceDeps.remove(dep);
        this.allLinkTargetDeps.remove(dep);
        this.allReferenceDep.remove(dep);
        this.allSharedDep.remove(dep);
        this.all....
        */
        
        if (withOpposite) {
            SmDependency symetric = dep.getSymetric();
            symetric.getOwner().removeDependency(symetric, false);
        }
    }

    /**
     * Access to the internal cache.
     * <p>
     * Do not mess with it.
     * @author cma
     * @since 3.7
     */
    @objid ("f88c56e4-1442-48ec-9106-e332c9f61342")
    public interface ICacheAccess {
        /**
         * Reset the cache.
         * <p>
         * Methods like {@link SmClass#getDependency(String)} won't work until {@link #init()} is called.
         */
        @objid ("6311de32-e2b3-4af3-98e7-ca43b57ab61c")
        void reset();

        /**
         * Initialize the cache
         */
        @objid ("d3479554-3441-40dd-b1aa-2982926acaf6")
        void init();

        /**
         * Reset and init the cache.
         */
        @objid ("6f78ada3-5a8e-4f44-a59a-4da6dd48c35f")
        void remake();

    }

}
