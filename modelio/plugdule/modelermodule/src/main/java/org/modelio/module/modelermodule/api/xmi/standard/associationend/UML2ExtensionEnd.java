/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.associationend;

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
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link AssociationEnd} with << UML2ExtensionEnd >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1e11d2ec-9ce3-43ed-8790-218d82be338b")
public class UML2ExtensionEnd {
    @objid ("ecc08503-7ce5-4a1e-ad38-5522b05ade15")
    public static final String STEREOTYPE_NAME = "UML2ExtensionEnd";

    /**
     * The underlying {@link AssociationEnd} represented by this proxy, never null.
     */
    @objid ("36fc2cea-5531-45c5-99df-28ff52a129eb")
    protected final AssociationEnd elt;

    /**
     * Tells whether a {@link UML2ExtensionEnd proxy} can be instantiated from a {@link MObject} checking it is a {@link AssociationEnd} stereotyped << UML2ExtensionEnd >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("d445b15a-fe94-4dfd-9490-bbfbe3c2355a")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof AssociationEnd) && ((AssociationEnd) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ExtensionEnd.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link AssociationEnd} stereotyped << UML2ExtensionEnd >> then instantiate a {@link UML2ExtensionEnd} proxy.
     * 
     * @return a {@link UML2ExtensionEnd} proxy on the created {@link AssociationEnd}.
     */
    @objid ("8a2501ef-ec03-4e5b-a93b-64e53f9c1059")
    public static UML2ExtensionEnd create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("AssociationEnd");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ExtensionEnd.STEREOTYPE_NAME);
        return UML2ExtensionEnd.instantiate((AssociationEnd)e);
    }

    /**
     * Tries to instantiate a {@link UML2ExtensionEnd} proxy from a {@link AssociationEnd} stereotyped << UML2ExtensionEnd >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a AssociationEnd
     * @return a {@link UML2ExtensionEnd} proxy or <i>null</i>.
     */
    @objid ("5878866e-d10e-42df-a381-0e713367533c")
    public static UML2ExtensionEnd instantiate(AssociationEnd obj) {
        return UML2ExtensionEnd.canInstantiate(obj) ? new UML2ExtensionEnd(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ExtensionEnd} proxy from a {@link AssociationEnd} stereotyped << UML2ExtensionEnd >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link AssociationEnd}
     * @return a {@link UML2ExtensionEnd} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("49b0f5c2-bf0b-4af7-b321-74b84ef7886d")
    public static UML2ExtensionEnd safeInstantiate(AssociationEnd obj) throws IllegalArgumentException {
        if (UML2ExtensionEnd.canInstantiate(obj))
        	return new UML2ExtensionEnd(obj);
        else
        	throw new IllegalArgumentException("UML2ExtensionEnd: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("e4cf00c5-53ca-4dd3-99d2-9abd2092f5f6")
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
        UML2ExtensionEnd other = (UML2ExtensionEnd) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link AssociationEnd}. 
     * @return the AssociationEnd represented by this proxy, never null.
     */
    @objid ("b04b5df0-a9ce-4a10-9ae6-396b6683520d")
    public AssociationEnd getElement() {
        return this.elt;
    }

    @objid ("ed4df8ad-9c13-43a1-818a-29d2aba24b1e")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("cf519f15-b9d0-4f7e-9948-1feaa0119dc4")
    protected UML2ExtensionEnd(AssociationEnd elt) {
        this.elt = elt;
    }

    @objid ("bd346fa3-cf3c-4b64-a0dc-310c1bd53e73")
    public static final class MdaTypes {
        @objid ("6bec5fd8-b2f8-4246-889a-f01498ba60c2")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("9f4b12a4-c158-4693-af32-5825481d5f44")
        private static Stereotype MDAASSOCDEP;

        @objid ("b562d450-9d64-46e2-8ef8-cd8ff4da2e4e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("b4aed4f2-8b3c-4972-a5c1-907b1b2fd8e4")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "770df309-5d0c-11df-a996-001302895b2b");
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
