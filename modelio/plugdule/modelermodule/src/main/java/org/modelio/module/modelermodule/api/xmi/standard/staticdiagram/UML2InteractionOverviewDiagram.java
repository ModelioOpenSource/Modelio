/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.staticdiagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.diagrams.StaticDiagram;
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
 * Proxy class to handle a {@link StaticDiagram} with << UML2InteractionOverviewDiagram  >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("025fc4e6-2df9-46ba-9efe-c6ce06c9b716")
public class UML2InteractionOverviewDiagram {
    @objid ("4b7e6840-a95b-4681-a0a8-3c03658ab168")
    public static final String STEREOTYPE_NAME = "UML2InteractionOverviewDiagram ";

    /**
     * The underlying {@link StaticDiagram} represented by this proxy, never null.
     */
    @objid ("9fcfdcd2-2615-450f-92ee-c49f09b1dde5")
    protected final StaticDiagram elt;

    /**
     * Tells whether a {@link UML2InteractionOverviewDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link StaticDiagram} stereotyped << UML2InteractionOverviewDiagram  >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("24d0d3e6-ee62-47c9-a47c-b72c24d896ec")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StaticDiagram) && ((StaticDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2InteractionOverviewDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StaticDiagram} stereotyped << UML2InteractionOverviewDiagram  >> then instantiate a {@link UML2InteractionOverviewDiagram} proxy.
     * 
     * @return a {@link UML2InteractionOverviewDiagram} proxy on the created {@link StaticDiagram}.
     */
    @objid ("cc90752b-c761-42bf-9848-b61d056e22a2")
    public static UML2InteractionOverviewDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("StaticDiagram");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2InteractionOverviewDiagram.STEREOTYPE_NAME);
        return UML2InteractionOverviewDiagram.instantiate((StaticDiagram)e);
    }

    /**
     * Tries to instantiate a {@link UML2InteractionOverviewDiagram} proxy from a {@link StaticDiagram} stereotyped << UML2InteractionOverviewDiagram  >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StaticDiagram
     * @return a {@link UML2InteractionOverviewDiagram} proxy or <i>null</i>.
     */
    @objid ("9550789e-0aa9-4f8e-beb6-9c1c2962aa59")
    public static UML2InteractionOverviewDiagram instantiate(StaticDiagram obj) {
        return UML2InteractionOverviewDiagram.canInstantiate(obj) ? new UML2InteractionOverviewDiagram(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2InteractionOverviewDiagram} proxy from a {@link StaticDiagram} stereotyped << UML2InteractionOverviewDiagram  >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link StaticDiagram}
     * @return a {@link UML2InteractionOverviewDiagram} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("b937246d-bd08-41c2-ba9c-419af249c814")
    public static UML2InteractionOverviewDiagram safeInstantiate(StaticDiagram obj) throws IllegalArgumentException {
        if (UML2InteractionOverviewDiagram.canInstantiate(obj))
        	return new UML2InteractionOverviewDiagram(obj);
        else
        	throw new IllegalArgumentException("UML2InteractionOverviewDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("54377e9c-edaf-4e69-9c98-453dab899769")
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
        UML2InteractionOverviewDiagram other = (UML2InteractionOverviewDiagram) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link StaticDiagram}. 
     * @return the StaticDiagram represented by this proxy, never null.
     */
    @objid ("ebccd1aa-f040-46a3-bccf-ddba8752ace5")
    public StaticDiagram getElement() {
        return this.elt;
    }

    @objid ("f09b10d9-755d-4688-ad37-0c747c422469")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("b57e64e3-b059-48a2-b844-6c304cfae52d")
    protected UML2InteractionOverviewDiagram(StaticDiagram elt) {
        this.elt = elt;
    }

    @objid ("be457845-c207-4134-ac6d-84fc582830cd")
    public static final class MdaTypes {
        @objid ("07fb98ba-1bba-4d00-ac57-5621bfaf923e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("400f9631-ef15-489e-a448-9baf938c2c55")
        private static Stereotype MDAASSOCDEP;

        @objid ("ff6a334b-5a7f-4235-8ed5-d4ca6456e38e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("6775bc2b-a686-413c-b002-83dc059444a6")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "e4358003-f3da-11df-8ada-0027103f347c");
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
