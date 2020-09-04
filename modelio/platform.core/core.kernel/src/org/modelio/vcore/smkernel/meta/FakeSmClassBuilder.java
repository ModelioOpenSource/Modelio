/* 
 * Copyright 2013-2018 Modeliosoft
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

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MetaclassAlreadyExistException;
import org.modelio.vcore.smkernel.meta.descriptor.MClassRef;
import org.modelio.vcore.smkernel.meta.fake.FakeSmClass;

/**
 * Builder design patter for building fake metaclasses.
 * <p>
 * To get one ask to the {@link SmMetamodel#fakeClassBuilder() metamodel}.
 * <p>
 * Usage:
 * <pre><code>
 * SmMetamodel mm = ...
 * SmClass fake = mm.fakeClassBuilder().setQualifiedName("mm.fragment.Missing").setCmsNode(false).build();
 * </code></pre>
 */
@objid ("2af0d1c8-a3ae-4748-aaeb-1fbb57e4d024")
public class FakeSmClassBuilder {
    @objid ("dcd6e149-fcef-4ec0-90be-268b4faba219")
    private String name;

    @objid ("8086adf6-d7c7-4656-88c0-5cb854d422e2")
    private boolean cmsNode;

    @objid ("eb54944e-1457-4267-a359-56bcf4bb6a48")
    private String fragmentName;

    @objid ("e751cc0a-bbdf-4766-91bf-5bdc632d368e")
    private SmMetamodel mm;

    @objid ("42205f37-32ed-4794-8b0a-2291682e12dd")
    protected FakeSmClassBuilder(SmMetamodel mm) {
        this.mm = mm;
    }

    /**
     * @param name the metaclass name
     * @return the receiver for further invocations
     */
    @objid ("3218f88f-4b98-4db6-ba8d-a4d8641607a6")
    public FakeSmClassBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Set the metaclass qualified name.
     * <p>
     * The qualified name must have be formated like "mm.fragment.metaclass name"
     * with the dot as separator.
     * @param qualifiedName the metaclass qualified name
     * @return the receiver for further invocations
     */
    @objid ("1f9fb9c8-8dec-40cf-a0bd-7fa09c410931")
    public FakeSmClassBuilder setQualifiedName(String qualifiedName) {
        MClassRef ref = MClassRef.fromQualifiedName(qualifiedName);
        this.name = ref.getClassName();
        this.fragmentName = ref.getFragmentName();
        return this;
    }

    /**
     * Set whether the metaclass is a CMS node.
     * <p>
     * By default it is not a CMS node.
     * @param val whether the metaclass is a CMS node.
     * @return the receiver for further invocations
     */
    @objid ("d2601a3a-e038-4110-ae89-19e8042099f4")
    public FakeSmClassBuilder setCmsNode(boolean val) {
        this.cmsNode = val;
        return this;
    }

    /**
     * Create and register the metaclass in the metamodel.
     * @return the built fake metaclass.
     * @throws org.modelio.vcore.smkernel.mapi.MetaclassAlreadyExistException if the metaclass already exists.
     */
    @objid ("bcea2c5d-5e1a-4bb1-a05d-6f5dcfb031f6")
    public SmClass build() throws MetaclassAlreadyExistException {
        Objects.requireNonNull(this.fragmentName);
        Objects.requireNonNull(this.name);
        
        ISmMetamodelFragment fakeFragment = this.mm.getFakeFragment(this.fragmentName, null);
        FakeSmClass cls = new FakeSmClass(fakeFragment, this.name, this.cmsNode);
        this.mm.addFakeMetaclass(cls);
        return cls;
    }

    @objid ("9803f0db-207d-4acc-9945-2fecab86211b")
    public FakeSmClassBuilder setFragmentName(String fragmentName) {
        this.fragmentName = fragmentName;
        return this;
    }

    @objid ("67b02b09-062b-400d-806f-1ab844e3d43d")
    protected final String getFragmentName() {
        return this.fragmentName;
    }

    @objid ("4cb21c63-92ff-43f8-aa72-0828a8ce0552")
    protected final SmMetamodel getMm() {
        return this.mm;
    }

    @objid ("66a4abb1-0953-4019-8974-737921d4c8ae")
    protected final String getName() {
        return this.name;
    }

    @objid ("7c9dd0e8-51b7-4ddf-976c-cdf158bcf506")
    protected final boolean isCmsNode() {
        return this.cmsNode;
    }

}
