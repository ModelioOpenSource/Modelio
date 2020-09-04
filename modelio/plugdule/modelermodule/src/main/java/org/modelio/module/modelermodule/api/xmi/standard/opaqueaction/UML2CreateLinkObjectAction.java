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
 * Proxy class to handle a {@link OpaqueAction} with << UML2CreateLinkObjectAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("d8f1f797-b904-47b7-a13f-1c46cc7fc8b7")
public class UML2CreateLinkObjectAction {
    @objid ("6acddeaf-2958-41fc-8a57-79ca0e93b09b")
    public static final String STEREOTYPE_NAME = "UML2CreateLinkObjectAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("91f171f6-0ef9-4968-8971-d6b907b3fd6c")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2CreateLinkObjectAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2CreateLinkObjectAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("d8e71af6-6b95-4c01-8b64-aa3354e5c23e")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2CreateLinkObjectAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2CreateLinkObjectAction >> then instantiate a {@link UML2CreateLinkObjectAction} proxy.
     * 
     * @return a {@link UML2CreateLinkObjectAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("e9ea7106-f8ea-4376-8271-9db3caf29d94")
    public static UML2CreateLinkObjectAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2CreateLinkObjectAction.STEREOTYPE_NAME);
        return UML2CreateLinkObjectAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2CreateLinkObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2CreateLinkObjectAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2CreateLinkObjectAction} proxy or <i>null</i>.
     */
    @objid ("773b13e1-7d63-4af2-ad82-b0e16cc0cae5")
    public static UML2CreateLinkObjectAction instantiate(OpaqueAction obj) {
        return UML2CreateLinkObjectAction.canInstantiate(obj) ? new UML2CreateLinkObjectAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2CreateLinkObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2CreateLinkObjectAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2CreateLinkObjectAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("14a0236d-fcef-478b-8cdd-056cede7c194")
    public static UML2CreateLinkObjectAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2CreateLinkObjectAction.canInstantiate(obj))
        	return new UML2CreateLinkObjectAction(obj);
        else
        	throw new IllegalArgumentException("UML2CreateLinkObjectAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("5157158f-cceb-417d-b354-0e25f6da462c")
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
        UML2CreateLinkObjectAction other = (UML2CreateLinkObjectAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("0aa3cef9-b628-407b-8f0d-1b6185a8e9be")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("b6c5e908-6416-4e68-b468-c834e6d78e4f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("252aeae3-9210-4f8d-a557-576c2d4a1a6e")
    protected UML2CreateLinkObjectAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("310f7147-2f6b-4605-9068-06aabbeaaf88")
    public static final class MdaTypes {
        @objid ("c0f0969f-bc91-4e2a-8512-8168ecb4a21c")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b6323951-5401-4297-bbc6-1f5d8106bc82")
        private static Stereotype MDAASSOCDEP;

        @objid ("f719a015-cc45-41fc-b4e9-2fdc420d2eca")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("e1e0d4df-f20b-4335-a47c-f2cca484bf3d")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "f8e58a85-c2fa-11de-8ac8-001302895b2b");
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
