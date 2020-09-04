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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReadVariableAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("369efe96-0462-4c40-b76c-8ace8f356825")
public class UML2ReadVariableAction {
    @objid ("100a06c6-1c89-419d-89f9-26a7a7fdbf67")
    public static final String STEREOTYPE_NAME = "UML2ReadVariableAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("34921afd-f2d6-4382-a509-61f89bf9ecf1")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReadVariableAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReadVariableAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("cba3e8ea-01ae-471b-b6b5-90aa6da19f7b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReadVariableAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReadVariableAction >> then instantiate a {@link UML2ReadVariableAction} proxy.
     * 
     * @return a {@link UML2ReadVariableAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("93dfab01-545e-4d3e-b046-d4400d0547c5")
    public static UML2ReadVariableAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReadVariableAction.STEREOTYPE_NAME);
        return UML2ReadVariableAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReadVariableAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadVariableAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReadVariableAction} proxy or <i>null</i>.
     */
    @objid ("f0818ad7-49bc-4d72-9551-03f5df00d01f")
    public static UML2ReadVariableAction instantiate(OpaqueAction obj) {
        return UML2ReadVariableAction.canInstantiate(obj) ? new UML2ReadVariableAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReadVariableAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadVariableAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReadVariableAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("cc73fdd3-b36b-4b11-8557-0bf3d7d91f10")
    public static UML2ReadVariableAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReadVariableAction.canInstantiate(obj))
        	return new UML2ReadVariableAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReadVariableAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("201b297e-a103-42e9-814d-5b1116754c5a")
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
        UML2ReadVariableAction other = (UML2ReadVariableAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("6551a631-6f7d-445e-950b-52fef524f85b")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("ffbefee2-260b-460c-9919-251cec0a6b7f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("9418efda-685c-4662-8ab8-7ef6ea2d6fc6")
    protected UML2ReadVariableAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("d499b2f6-8b56-4a50-a4f2-9b0acefb27f3")
    public static final class MdaTypes {
        @objid ("e4ab71b3-f050-4c73-8de7-4d1ab8752c10")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("16ba061c-5b9f-43f9-a255-61589bedd740")
        private static Stereotype MDAASSOCDEP;

        @objid ("f45891d7-4ebe-4687-87d1-302efb915037")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("37bf7177-8ac3-41e0-8c38-e449b5bb803f")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "1abd18db-c2fd-11de-8ac8-001302895b2b");
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
