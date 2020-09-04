/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.analyst.infrastructure.dependency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Dependency} with << derive >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("0a2b7f32-1cd2-44cd-b61d-f4464bbf91de")
public class Derive {
    @objid ("6233863a-dcec-4d2b-977e-973918b3572d")
    public static final String STEREOTYPE_NAME = "derive";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("962922e3-040c-42d8-9b47-706a21999d00")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Derive proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << derive >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("e68a9f58-e772-4e03-b2e9-0f95f7b80557")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Derive.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << derive >> then instantiate a {@link Derive} proxy.
     * 
     * @return a {@link Derive} proxy on the created {@link Dependency}.
     */
    @objid ("53a53adb-7168-4147-b339-c2a710fcbab0")
    public static Derive create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Derive.STEREOTYPE_NAME);
        return Derive.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Derive} proxy from a {@link Dependency} stereotyped << derive >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Derive} proxy or <i>null</i>.
     */
    @objid ("459886eb-302d-48b2-a1ed-baa6aa67fd40")
    public static Derive instantiate(Dependency obj) {
        return Derive.canInstantiate(obj) ? new Derive(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Derive} proxy from a {@link Dependency} stereotyped << derive >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Derive} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("1ef28869-c745-45cf-9dc2-df5ca032d21b")
    public static Derive safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Derive.canInstantiate(obj))
        	return new Derive(obj);
        else
        	throw new IllegalArgumentException("Derive: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("d65d1a58-e895-4172-8ec7-7cf4bb4d68b8")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Derive other = (Derive) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("94065846-5634-42a4-8991-f12f82b7c2aa")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("f16a77d2-50e0-4fd1-9581-80bb1a5e27af")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("eefa6e1c-6969-4118-b0e6-dc2a784de606")
    protected Derive(Dependency elt) {
        this.elt = elt;
    }

    @objid ("7dea825e-1f89-4805-9f37-7ff8a5c37024")
    public static final class MdaTypes {
        @objid ("e83613b5-62a7-46a3-b58c-1084602d5763")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("440e6d51-8f9a-4a4a-9973-fc805611e3aa")
        private static Stereotype MDAASSOCDEP;

        @objid ("c15a8a98-ba21-4f45-867c-113690b628ef")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("42b84bc1-efc3-4fb2-b0b3-ffce7b1b9f44")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-021a-0000-000000000000");
            MDAASSOCDEP = ctx.getModelingSession().findElementById(Stereotype.class, "94b7efa5-f94c-4d1d-896f-f103e56a8e2e");
            MDAASSOCDEP_ROLE = ctx.getModelingSession().findElementById(TagType.class, "7637f2fd-b750-43c1-a15c-5d0b084ca1cd");
        }


	static {
		if(ModelerModuleModule.getInstance() != null) {
			init(ModelerModuleModule.getInstance().getModuleContext());
		}
	}
    }

}
