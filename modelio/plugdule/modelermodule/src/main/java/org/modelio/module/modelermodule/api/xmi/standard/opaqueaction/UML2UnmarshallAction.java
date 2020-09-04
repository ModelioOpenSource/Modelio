/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.opaqueaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction;
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
 * Proxy class to handle a {@link OpaqueAction} with << UML2UnmarshallAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("79f49b91-19a8-4376-b6f1-84c1d30a8534")
public class UML2UnmarshallAction {
    @objid ("c1ced4d6-b8a9-4040-af7a-d4074699b8da")
    public static final String STEREOTYPE_NAME = "UML2UnmarshallAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("e4a72c35-0cc8-4f39-9bff-f8d0016dcbd3")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2UnmarshallAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2UnmarshallAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("7e60b4db-9920-488d-a938-84a9fc264e71")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2UnmarshallAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2UnmarshallAction >> then instantiate a {@link UML2UnmarshallAction} proxy.
     * 
     * @return a {@link UML2UnmarshallAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("0c67c642-474c-4fb8-933d-edfbe37fcd09")
    public static UML2UnmarshallAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2UnmarshallAction.STEREOTYPE_NAME);
        return UML2UnmarshallAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2UnmarshallAction} proxy from a {@link OpaqueAction} stereotyped << UML2UnmarshallAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2UnmarshallAction} proxy or <i>null</i>.
     */
    @objid ("d5b021f6-7c27-4fc3-98c8-03c4e4172dfc")
    public static UML2UnmarshallAction instantiate(OpaqueAction obj) {
        return UML2UnmarshallAction.canInstantiate(obj) ? new UML2UnmarshallAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2UnmarshallAction} proxy from a {@link OpaqueAction} stereotyped << UML2UnmarshallAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2UnmarshallAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("6b74eeb5-cb56-40a9-8574-bacc78f08650")
    public static UML2UnmarshallAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2UnmarshallAction.canInstantiate(obj))
        	return new UML2UnmarshallAction(obj);
        else
        	throw new IllegalArgumentException("UML2UnmarshallAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("39d95e07-d575-486d-83c6-e4bdae87a6f3")
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
        UML2UnmarshallAction other = (UML2UnmarshallAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("f91d1c49-d7fe-469a-8ecb-fbd1254704a6")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("c81dd705-4f16-4699-97fb-50b9d9e82797")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("cef4e1bb-6721-47f9-8206-c721295b5010")
    protected UML2UnmarshallAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("3dccb5eb-58b9-4a9d-ae64-d0336e7eb538")
    public static final class MdaTypes {
        @objid ("3e75b6b8-906e-4d74-920d-4bcbb0dec523")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("47609198-2e2c-4c17-a23e-7110f111c95b")
        private static Stereotype MDAASSOCDEP;

        @objid ("d57f88fd-bfee-4038-91fd-72e71016e2d5")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("fc126234-43e5-4330-a87c-219b5ab3ea1a")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "76c174ab-c2fd-11de-8ac8-001302895b2b");
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
