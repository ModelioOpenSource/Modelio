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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReclassifyObjectAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("252a50bd-33e4-44b6-8714-24f08fe1b5b4")
public class UML2ReclassifyObjectAction {
    @objid ("c730d596-0c4a-4df2-a5b0-c8c14bc34e94")
    public static final String STEREOTYPE_NAME = "UML2ReclassifyObjectAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("00be7cff-9a68-451a-8965-214c8c7bf5bb")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReclassifyObjectAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReclassifyObjectAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("08dfc47d-6bff-4b22-bca9-1504415ccb08")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReclassifyObjectAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReclassifyObjectAction >> then instantiate a {@link UML2ReclassifyObjectAction} proxy.
     * 
     * @return a {@link UML2ReclassifyObjectAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("f1b86186-f34b-4af6-9a91-d8ef284a7e80")
    public static UML2ReclassifyObjectAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReclassifyObjectAction.STEREOTYPE_NAME);
        return UML2ReclassifyObjectAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReclassifyObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReclassifyObjectAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReclassifyObjectAction} proxy or <i>null</i>.
     */
    @objid ("3fc6ddb4-137e-4d2f-a800-45d770cd6a72")
    public static UML2ReclassifyObjectAction instantiate(OpaqueAction obj) {
        return UML2ReclassifyObjectAction.canInstantiate(obj) ? new UML2ReclassifyObjectAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReclassifyObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReclassifyObjectAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReclassifyObjectAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("22cf3257-2658-40cb-a41d-2b5d351ae498")
    public static UML2ReclassifyObjectAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReclassifyObjectAction.canInstantiate(obj))
        	return new UML2ReclassifyObjectAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReclassifyObjectAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("ed82cf49-1a8e-40c8-a994-d6621d25b5dd")
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
        UML2ReclassifyObjectAction other = (UML2ReclassifyObjectAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("daae8b99-835d-4a96-aa28-a09238808e80")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("58e94894-24f8-4945-864a-33fcbfcd31d9")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("20551d74-6922-4218-937f-a66933c086b4")
    protected UML2ReclassifyObjectAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("f677dc83-6f25-4b5e-9fdf-c82ec9c91bd4")
    public static final class MdaTypes {
        @objid ("74fae9a3-c492-4971-8fc3-0a56f74e32a3")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("67f546fb-f99b-486c-9ac4-25453addd06b")
        private static Stereotype MDAASSOCDEP;

        @objid ("94593efb-8739-4790-823a-161fa7ff1f38")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("bd7bb517-0413-45f7-a1d9-d2937c538c6b")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "229bc921-c2fd-11de-8ac8-001302895b2b");
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
