/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.activitynode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
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
 * Proxy class to handle a {@link ActivityNode} with << UML2Body >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("83b25ebe-46ce-430e-986a-831f705f3318")
public class UML2Body {
    @objid ("d70c13fc-93c8-455c-b02c-61c3a739eab5")
    public static final String STEREOTYPE_NAME = "UML2Body";

    /**
     * The underlying {@link ActivityNode} represented by this proxy, never null.
     */
    @objid ("3ca6f80b-d036-4edc-aa4d-a6810fb215f3")
    protected final ActivityNode elt;

    /**
     * Tells whether a {@link UML2Body proxy} can be instantiated from a {@link MObject} checking it is a {@link ActivityNode} stereotyped << UML2Body >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("69f823e9-ed0b-45df-bb83-3e7b8ba5f9b2")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ActivityNode) && ((ActivityNode) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Body.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ActivityNode} stereotyped << UML2Body >> then instantiate a {@link UML2Body} proxy.
     * 
     * @return a {@link UML2Body} proxy on the created {@link ActivityNode}.
     */
    @objid ("b19e0a41-76dc-469d-be9d-f36c47f32baa")
    public static UML2Body create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("ActivityNode");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Body.STEREOTYPE_NAME);
        return UML2Body.instantiate((ActivityNode)e);
    }

    /**
     * Tries to instantiate a {@link UML2Body} proxy from a {@link ActivityNode} stereotyped << UML2Body >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ActivityNode
     * @return a {@link UML2Body} proxy or <i>null</i>.
     */
    @objid ("543eb197-260f-4815-8027-43dc548a5a35")
    public static UML2Body instantiate(ActivityNode obj) {
        return UML2Body.canInstantiate(obj) ? new UML2Body(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Body} proxy from a {@link ActivityNode} stereotyped << UML2Body >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ActivityNode}
     * @return a {@link UML2Body} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("cd87e824-59d5-4a3a-86b5-2feac5ac31d9")
    public static UML2Body safeInstantiate(ActivityNode obj) throws IllegalArgumentException {
        if (UML2Body.canInstantiate(obj))
        	return new UML2Body(obj);
        else
        	throw new IllegalArgumentException("UML2Body: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("0d5905c9-8d7f-4578-a63a-45551b5b2f2f")
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
        UML2Body other = (UML2Body) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ActivityNode}. 
     * @return the ActivityNode represented by this proxy, never null.
     */
    @objid ("33131462-c26b-48ee-8f42-dace53ad921b")
    public ActivityNode getElement() {
        return this.elt;
    }

    @objid ("499be702-9c98-49b0-aa23-572007cff327")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("2106b5b6-1cc9-4ca5-8eae-aebaa7eb1524")
    protected UML2Body(ActivityNode elt) {
        this.elt = elt;
    }

    @objid ("8331b437-2365-45fa-b380-c464635804d8")
    public static final class MdaTypes {
        @objid ("453742ae-d343-4a86-8b58-75329efecdf6")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("64909184-06ca-4b3f-868c-0d1013c07091")
        private static Stereotype MDAASSOCDEP;

        @objid ("cbc02e5b-8afc-4533-9f93-7094902b7eea")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("6f19c45a-e16d-4304-a675-939a4c1cc099")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "76f275f9-32d9-11e0-91f3-0027103f347c");
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
