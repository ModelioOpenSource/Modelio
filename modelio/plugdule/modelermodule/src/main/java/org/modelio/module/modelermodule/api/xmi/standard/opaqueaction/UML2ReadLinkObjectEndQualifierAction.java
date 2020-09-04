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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReadLinkObjectEndQualifierAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("f2df080d-78a1-4a10-bc63-92b34862282d")
public class UML2ReadLinkObjectEndQualifierAction {
    @objid ("a668f074-17a9-416d-bbdf-953bd8502b48")
    public static final String STEREOTYPE_NAME = "UML2ReadLinkObjectEndQualifierAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("fed06bad-1529-4960-a38f-aa4b28432b5d")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReadLinkObjectEndQualifierAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReadLinkObjectEndQualifierAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("e4b21c49-e2f9-49d0-8f4f-ff4a9a09349a")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReadLinkObjectEndQualifierAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReadLinkObjectEndQualifierAction >> then instantiate a {@link UML2ReadLinkObjectEndQualifierAction} proxy.
     * 
     * @return a {@link UML2ReadLinkObjectEndQualifierAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("4e315c23-83ea-4e7c-93ed-2dfa0ffd8dca")
    public static UML2ReadLinkObjectEndQualifierAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReadLinkObjectEndQualifierAction.STEREOTYPE_NAME);
        return UML2ReadLinkObjectEndQualifierAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReadLinkObjectEndQualifierAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadLinkObjectEndQualifierAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReadLinkObjectEndQualifierAction} proxy or <i>null</i>.
     */
    @objid ("80a9ab67-ccc7-4230-a866-5d058fcb45eb")
    public static UML2ReadLinkObjectEndQualifierAction instantiate(OpaqueAction obj) {
        return UML2ReadLinkObjectEndQualifierAction.canInstantiate(obj) ? new UML2ReadLinkObjectEndQualifierAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReadLinkObjectEndQualifierAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadLinkObjectEndQualifierAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReadLinkObjectEndQualifierAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("457e4b20-5933-4784-b907-210ddf7b53ee")
    public static UML2ReadLinkObjectEndQualifierAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReadLinkObjectEndQualifierAction.canInstantiate(obj))
        	return new UML2ReadLinkObjectEndQualifierAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReadLinkObjectEndQualifierAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("b2520863-6a7f-496b-b2b8-c6aaafd2e0ef")
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
        UML2ReadLinkObjectEndQualifierAction other = (UML2ReadLinkObjectEndQualifierAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("c0c18a92-21a2-4557-823b-354466b34df1")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("e86a8af5-811c-45a7-a766-f679959e0b74")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("ea434c13-3a3b-4937-b28f-294071447e93")
    protected UML2ReadLinkObjectEndQualifierAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("5227cd16-da95-4b40-a5e0-22322a3d69eb")
    public static final class MdaTypes {
        @objid ("8d53b2b8-40e0-42b2-a257-b9d5d624ee4e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("f873619c-7072-4fc6-89bf-777bb9b371c5")
        private static Stereotype MDAASSOCDEP;

        @objid ("28144605-2f76-4609-a6ab-36419cc8290b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("ed72ce62-1d4c-4a16-b345-4fb653beb724")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "06edcdd9-c2fd-11de-8ac8-001302895b2b");
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
