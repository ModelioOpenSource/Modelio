/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.generalization;

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
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Generalization} with << implementation >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("537f865d-60d7-49df-a0f5-0faa2f98dc42")
public class Implementation {
    @objid ("b105a62b-5537-442c-948f-8e8afdfb1e52")
    public static final String STEREOTYPE_NAME = "implementation";

    /**
     * The underlying {@link Generalization} represented by this proxy, never null.
     */
    @objid ("9243d1e0-49ac-4e9d-a016-9523dfa7ec58")
    protected final Generalization elt;

    /**
     * Tells whether a {@link Implementation proxy} can be instantiated from a {@link MObject} checking it is a {@link Generalization} stereotyped << implementation >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("b86aa7f7-47e4-4fb9-84ff-c9f4728979e2")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Generalization) && ((Generalization) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Implementation.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Generalization} stereotyped << implementation >> then instantiate a {@link Implementation} proxy.
     * 
     * @return a {@link Implementation} proxy on the created {@link Generalization}.
     */
    @objid ("288dbbe4-4390-47a0-befa-807fff770182")
    public static Implementation create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Generalization");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Implementation.STEREOTYPE_NAME);
        return Implementation.instantiate((Generalization)e);
    }

    /**
     * Tries to instantiate a {@link Implementation} proxy from a {@link Generalization} stereotyped << implementation >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Generalization
     * @return a {@link Implementation} proxy or <i>null</i>.
     */
    @objid ("2d80abad-b9f9-473b-8dc5-8309796cf50b")
    public static Implementation instantiate(Generalization obj) {
        return Implementation.canInstantiate(obj) ? new Implementation(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Implementation} proxy from a {@link Generalization} stereotyped << implementation >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Generalization}
     * @return a {@link Implementation} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("d742bad7-ca91-43cb-ad8c-c7793b70fd33")
    public static Implementation safeInstantiate(Generalization obj) throws IllegalArgumentException {
        if (Implementation.canInstantiate(obj))
        	return new Implementation(obj);
        else
        	throw new IllegalArgumentException("Implementation: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("617f7d9f-f8cc-443f-9a13-87b28239a5b7")
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
        Implementation other = (Implementation) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Generalization}. 
     * @return the Generalization represented by this proxy, never null.
     */
    @objid ("ea65c5d6-9502-4a70-bf5b-dec8778aa476")
    public Generalization getElement() {
        return this.elt;
    }

    @objid ("5ae71194-fac6-43ea-801f-c71ce16d2f6e")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("9e751885-7c6e-4c65-85e6-85ff61820301")
    protected Implementation(Generalization elt) {
        this.elt = elt;
    }

    @objid ("38988b48-d6c0-416a-a9ab-c1d8cde9859c")
    public static final class MdaTypes {
        @objid ("aa676eec-6128-4193-966c-e1f91792c87b")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("85ce53cf-3213-4a18-81a0-cda85fc076ee")
        private static Stereotype MDAASSOCDEP;

        @objid ("952c6ea3-097c-4e1f-bfe6-c8a8507be63f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("63c90bf9-9046-422c-9e75-adaa58640fc1")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01c7-0000-000000000000");
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
