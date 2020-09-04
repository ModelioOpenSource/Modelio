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
 * Proxy class to handle a {@link Dependency} with << guarantee >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("59095dca-f0dc-4be2-a58b-5035f5929642")
public class Guarantee {
    @objid ("6f6a131c-e2f1-493c-b93b-7530fc53218b")
    public static final String STEREOTYPE_NAME = "guarantee";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("1f0209f7-a731-4414-a511-7a51f99aad24")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Guarantee proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << guarantee >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("38ef120b-d0f6-450d-b386-d73557b54af8")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Guarantee.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << guarantee >> then instantiate a {@link Guarantee} proxy.
     * 
     * @return a {@link Guarantee} proxy on the created {@link Dependency}.
     */
    @objid ("daa968e3-a906-472b-9dbb-6c9478c5a96c")
    public static Guarantee create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Guarantee.STEREOTYPE_NAME);
        return Guarantee.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Guarantee} proxy from a {@link Dependency} stereotyped << guarantee >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Guarantee} proxy or <i>null</i>.
     */
    @objid ("d06cacd2-b2ad-4bd9-acb3-4d4cffa6ef6d")
    public static Guarantee instantiate(Dependency obj) {
        return Guarantee.canInstantiate(obj) ? new Guarantee(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Guarantee} proxy from a {@link Dependency} stereotyped << guarantee >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Guarantee} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("16cffdbd-f370-4f56-a4b0-0e06ba409ca2")
    public static Guarantee safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Guarantee.canInstantiate(obj))
        	return new Guarantee(obj);
        else
        	throw new IllegalArgumentException("Guarantee: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("873f9d73-759b-4420-85a9-7de27d1e65d4")
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
        Guarantee other = (Guarantee) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("df7f7780-e73c-427f-999f-b0701ae6118c")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("4c38ba6e-603b-48d6-9d5d-72e1cd36ad03")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("14a73f99-ae45-4b87-977c-6c32df6cd1b0")
    protected Guarantee(Dependency elt) {
        this.elt = elt;
    }

    @objid ("d5bd302d-6ad5-4ad1-bfa4-bca43f79b810")
    public static final class MdaTypes {
        @objid ("07afb1bb-bcc4-41b9-b9b7-c4715cd806ef")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("f626809a-8beb-4cb3-9b53-f8dadd2b7561")
        private static Stereotype MDAASSOCDEP;

        @objid ("425374db-3be4-49a9-89f8-22be1d90ce8a")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("08c8f801-802a-4f4a-8840-30ec95a4c821")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0251-0000-000000000000");
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
