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
 * Proxy class to handle a {@link OpaqueAction} with << UML2DestroyLinkAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("9a9af42c-042a-4e5c-b598-80ac68b8af16")
public class UML2DestroyLinkAction {
    @objid ("bd687699-059a-4d8a-9e52-043841e22272")
    public static final String STEREOTYPE_NAME = "UML2DestroyLinkAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("e74f547e-ee81-4500-813f-e379479998a3")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2DestroyLinkAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2DestroyLinkAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("04eac3b9-dc39-4b87-942b-f03d3a9ffb5c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2DestroyLinkAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2DestroyLinkAction >> then instantiate a {@link UML2DestroyLinkAction} proxy.
     * 
     * @return a {@link UML2DestroyLinkAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("4173e965-f0ec-4010-b446-442739f55029")
    public static UML2DestroyLinkAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2DestroyLinkAction.STEREOTYPE_NAME);
        return UML2DestroyLinkAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2DestroyLinkAction} proxy from a {@link OpaqueAction} stereotyped << UML2DestroyLinkAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2DestroyLinkAction} proxy or <i>null</i>.
     */
    @objid ("cf0605fe-ec17-4758-8ec2-509c101edc97")
    public static UML2DestroyLinkAction instantiate(OpaqueAction obj) {
        return UML2DestroyLinkAction.canInstantiate(obj) ? new UML2DestroyLinkAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2DestroyLinkAction} proxy from a {@link OpaqueAction} stereotyped << UML2DestroyLinkAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2DestroyLinkAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("80baba92-ceaf-4836-9753-ea2836a9a5ed")
    public static UML2DestroyLinkAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2DestroyLinkAction.canInstantiate(obj))
        	return new UML2DestroyLinkAction(obj);
        else
        	throw new IllegalArgumentException("UML2DestroyLinkAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("3ed57f9b-9335-4488-b17e-2d53e908759a")
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
        UML2DestroyLinkAction other = (UML2DestroyLinkAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("e00f452c-751c-4b5b-9615-c448c5a4b540")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("3acb4263-afbe-4653-b2e0-36f60e869fe6")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("5da94d1e-d38b-45a6-af1e-1525c18452e5")
    protected UML2DestroyLinkAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("32304d94-dbf4-4c93-af43-6a718691d283")
    public static final class MdaTypes {
        @objid ("41279d19-7b0c-4000-9acd-b32ea50f2b43")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("7c9b6fdc-9441-4935-b081-ad5fc97a6931")
        private static Stereotype MDAASSOCDEP;

        @objid ("e082b036-5771-47c9-88ba-91c3b6ab7eaa")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("6fa0ba64-48ae-42b2-818a-1cb2c02c8744")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "744f6321-c2f9-11de-8ac8-001302895b2b");
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
