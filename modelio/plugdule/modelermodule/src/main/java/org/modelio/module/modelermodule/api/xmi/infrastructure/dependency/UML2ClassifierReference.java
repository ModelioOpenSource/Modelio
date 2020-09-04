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
 * Proxy class to handle a {@link Dependency} with << UML2ClassifierReference >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("ef0b69f8-a0bc-4e50-aa2f-4bd55a6ed606")
public class UML2ClassifierReference {
    @objid ("387940ba-f94e-4ad9-916f-76317427f283")
    public static final String STEREOTYPE_NAME = "UML2ClassifierReference";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("ca871cea-3e6f-45de-ad5b-4882d6064f67")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2ClassifierReference proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2ClassifierReference >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("c3678d98-044b-4df6-9c42-715b0083caaf")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ClassifierReference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2ClassifierReference >> then instantiate a {@link UML2ClassifierReference} proxy.
     * 
     * @return a {@link UML2ClassifierReference} proxy on the created {@link Dependency}.
     */
    @objid ("8f2b7192-ff2d-4da6-8661-91ef920362aa")
    public static UML2ClassifierReference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ClassifierReference.STEREOTYPE_NAME);
        return UML2ClassifierReference.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2ClassifierReference} proxy from a {@link Dependency} stereotyped << UML2ClassifierReference >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2ClassifierReference} proxy or <i>null</i>.
     */
    @objid ("b5ce7396-9ec2-4dd7-9959-54213eea6184")
    public static UML2ClassifierReference instantiate(Dependency obj) {
        return UML2ClassifierReference.canInstantiate(obj) ? new UML2ClassifierReference(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ClassifierReference} proxy from a {@link Dependency} stereotyped << UML2ClassifierReference >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2ClassifierReference} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("b8f5c65d-4d59-422f-a020-3c489420ee74")
    public static UML2ClassifierReference safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2ClassifierReference.canInstantiate(obj))
        	return new UML2ClassifierReference(obj);
        else
        	throw new IllegalArgumentException("UML2ClassifierReference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("13c0cf72-0897-41f9-8501-cfaf47728192")
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
        UML2ClassifierReference other = (UML2ClassifierReference) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("ac960666-f55a-43f6-9208-32aa4dec91c3")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("833d0488-552e-4e23-a2de-ce8477f6a5fc")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("9d8f46fa-385f-46c6-8772-0f9586e853ac")
    protected UML2ClassifierReference(Dependency elt) {
        this.elt = elt;
    }

    @objid ("218b6658-c8ad-417a-877f-66b90843c474")
    public static final class MdaTypes {
        @objid ("ade40afd-0789-4381-90ca-196ef3dcaa67")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("60f326a0-9e77-48b3-9944-e2dccf87f748")
        private static Stereotype MDAASSOCDEP;

        @objid ("90a5780c-36a2-4124-8d21-1eacb8a9436d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f260dc92-1101-4c44-b268-bc7af8e2eaab")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "ed95fa9b-de99-11de-905b-001302895b2b");
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
