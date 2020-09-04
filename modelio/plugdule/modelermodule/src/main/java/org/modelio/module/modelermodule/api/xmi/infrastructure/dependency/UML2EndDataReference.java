/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << UML2EndData_Reference >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("21ba6909-eafd-4ef8-9976-b21672b3d76a")
public class UML2EndDataReference {
    @objid ("eff8dcea-1eee-4172-87a6-2063000f6741")
    public static final String STEREOTYPE_NAME = "UML2EndData_Reference";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("2c969075-dc0e-4fde-83fc-b7077bc69b03")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2EndDataReference proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2EndData_Reference >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("d2f0d83b-cfb7-4fc7-8b6c-64c6365e32ce")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2EndDataReference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2EndData_Reference >> then instantiate a {@link UML2EndDataReference} proxy.
     * 
     * @return a {@link UML2EndDataReference} proxy on the created {@link Dependency}.
     */
    @objid ("8ed93de7-aae4-4f3e-a82e-9d2365045ee9")
    public static UML2EndDataReference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2EndDataReference.STEREOTYPE_NAME);
        return UML2EndDataReference.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2EndDataReference} proxy from a {@link Dependency} stereotyped << UML2EndData_Reference >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2EndDataReference} proxy or <i>null</i>.
     */
    @objid ("b7209822-87b1-419d-a888-302c1876676a")
    public static UML2EndDataReference instantiate(Dependency obj) {
        return UML2EndDataReference.canInstantiate(obj) ? new UML2EndDataReference(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2EndDataReference} proxy from a {@link Dependency} stereotyped << UML2EndData_Reference >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2EndDataReference} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("dac505f0-f24f-4632-a76e-81bbcec64caa")
    public static UML2EndDataReference safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2EndDataReference.canInstantiate(obj))
        	return new UML2EndDataReference(obj);
        else
        	throw new IllegalArgumentException("UML2EndDataReference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("dcfb4789-d42b-4f7e-9b70-891088baf9e4")
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
        UML2EndDataReference other = (UML2EndDataReference) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("5ecb8ba4-4caf-4303-9fcc-617d8a64b2a0")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("f5b6ee82-6d1d-4a5f-a434-5323c3aff143")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("aa423124-bb52-4d28-9c0d-689ea24cce42")
    protected UML2EndDataReference(Dependency elt) {
        this.elt = elt;
    }

    @objid ("fbf92a3f-3305-4158-b8a7-11cae3ac1b3b")
    public static final class MdaTypes {
        @objid ("425f9853-48c2-4057-bacd-53bcca78d232")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("44123de9-3604-4b18-8d4a-4942f10f202d")
        private static Stereotype MDAASSOCDEP;

        @objid ("d700bf8c-b139-49bf-b278-c1abd7c934fd")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3ea9cd95-32e2-4f5a-903d-824bc20844d1")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "5d167c0f-df53-11de-b2b1-001302895b2b");
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
