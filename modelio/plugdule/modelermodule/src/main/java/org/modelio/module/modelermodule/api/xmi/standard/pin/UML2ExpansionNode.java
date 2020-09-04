/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.pin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.Pin;
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
 * Proxy class to handle a {@link Pin} with << UML2ExpansionNode >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("c1c157dd-3d9f-46e4-ae7e-b086613744bc")
public class UML2ExpansionNode {
    @objid ("d2411992-6489-4ca1-bb9a-468ea142db87")
    public static final String STEREOTYPE_NAME = "UML2ExpansionNode";

    /**
     * The underlying {@link Pin} represented by this proxy, never null.
     */
    @objid ("6b02113a-c148-4e25-a328-530f9dad6f3b")
    protected final Pin elt;

    /**
     * Tells whether a {@link UML2ExpansionNode proxy} can be instantiated from a {@link MObject} checking it is a {@link Pin} stereotyped << UML2ExpansionNode >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("ba56377c-2199-45fd-81d7-75f26ce331eb")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Pin) && ((Pin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ExpansionNode.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Pin} stereotyped << UML2ExpansionNode >> then instantiate a {@link UML2ExpansionNode} proxy.
     * 
     * @return a {@link UML2ExpansionNode} proxy on the created {@link Pin}.
     */
    @objid ("f5a6490d-c62c-4c5e-8761-a93b78177e15")
    public static UML2ExpansionNode create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Pin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ExpansionNode.STEREOTYPE_NAME);
        return UML2ExpansionNode.instantiate((Pin)e);
    }

    /**
     * Tries to instantiate a {@link UML2ExpansionNode} proxy from a {@link Pin} stereotyped << UML2ExpansionNode >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Pin
     * @return a {@link UML2ExpansionNode} proxy or <i>null</i>.
     */
    @objid ("fda74f6c-9e20-4895-b2e7-ce417cde1f85")
    public static UML2ExpansionNode instantiate(Pin obj) {
        return UML2ExpansionNode.canInstantiate(obj) ? new UML2ExpansionNode(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ExpansionNode} proxy from a {@link Pin} stereotyped << UML2ExpansionNode >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Pin}
     * @return a {@link UML2ExpansionNode} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("d9545a53-80d5-4490-8624-ce1c84bf27a9")
    public static UML2ExpansionNode safeInstantiate(Pin obj) throws IllegalArgumentException {
        if (UML2ExpansionNode.canInstantiate(obj))
        	return new UML2ExpansionNode(obj);
        else
        	throw new IllegalArgumentException("UML2ExpansionNode: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("9ae88528-d8fb-4967-8d1b-1a845e1282bd")
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
        UML2ExpansionNode other = (UML2ExpansionNode) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Pin}. 
     * @return the Pin represented by this proxy, never null.
     */
    @objid ("5da5ff75-6f29-4c56-8877-5ad66422f666")
    public Pin getElement() {
        return this.elt;
    }

    @objid ("18e00566-7363-41ee-96b4-dbe06518e718")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("b0c9b74d-d92d-4b56-954c-67befaa66d67")
    protected UML2ExpansionNode(Pin elt) {
        this.elt = elt;
    }

    @objid ("6697fd3d-89c7-4e82-8ddc-c23cc8607673")
    public static final class MdaTypes {
        @objid ("a6c73aed-da98-4c06-a9c1-ecff203c18c1")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("f6b47515-e0d3-48b3-a1bf-db09221576e2")
        private static Stereotype MDAASSOCDEP;

        @objid ("9ae96869-8a47-4b60-9f7f-6f06ebf752ea")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7fe6672e-5d17-4379-a8e8-be24f902e710")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "1b1ba62d-205e-11df-948e-001302895b2b");
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
