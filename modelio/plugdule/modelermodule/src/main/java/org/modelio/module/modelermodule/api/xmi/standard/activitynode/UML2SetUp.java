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
 * Proxy class to handle a {@link ActivityNode} with << UML2SetUp >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("eb6de59a-6ea2-4168-8059-82456e9bf47e")
public class UML2SetUp {
    @objid ("9c133b47-5fc9-478c-a7a8-d38d6d9aeddd")
    public static final String STEREOTYPE_NAME = "UML2SetUp";

    /**
     * The underlying {@link ActivityNode} represented by this proxy, never null.
     */
    @objid ("e7caa7c8-2b24-4413-b2e1-d7a507775fbe")
    protected final ActivityNode elt;

    /**
     * Tells whether a {@link UML2SetUp proxy} can be instantiated from a {@link MObject} checking it is a {@link ActivityNode} stereotyped << UML2SetUp >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("6b55d131-8ce8-4ca6-b5ae-08796465affc")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ActivityNode) && ((ActivityNode) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2SetUp.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ActivityNode} stereotyped << UML2SetUp >> then instantiate a {@link UML2SetUp} proxy.
     * 
     * @return a {@link UML2SetUp} proxy on the created {@link ActivityNode}.
     */
    @objid ("f7398ebb-163c-437a-b9ac-f52d104eca64")
    public static UML2SetUp create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("ActivityNode");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2SetUp.STEREOTYPE_NAME);
        return UML2SetUp.instantiate((ActivityNode)e);
    }

    /**
     * Tries to instantiate a {@link UML2SetUp} proxy from a {@link ActivityNode} stereotyped << UML2SetUp >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ActivityNode
     * @return a {@link UML2SetUp} proxy or <i>null</i>.
     */
    @objid ("97471708-9834-4f22-9a50-c5e6b6feaaec")
    public static UML2SetUp instantiate(ActivityNode obj) {
        return UML2SetUp.canInstantiate(obj) ? new UML2SetUp(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2SetUp} proxy from a {@link ActivityNode} stereotyped << UML2SetUp >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ActivityNode}
     * @return a {@link UML2SetUp} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("6e599a93-a6e8-4ac6-baa3-a761d8422bef")
    public static UML2SetUp safeInstantiate(ActivityNode obj) throws IllegalArgumentException {
        if (UML2SetUp.canInstantiate(obj))
        	return new UML2SetUp(obj);
        else
        	throw new IllegalArgumentException("UML2SetUp: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("f3851b2e-d63c-416f-ae0a-969db3c4e6c6")
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
        UML2SetUp other = (UML2SetUp) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ActivityNode}. 
     * @return the ActivityNode represented by this proxy, never null.
     */
    @objid ("2604e594-1604-4f1a-bc86-2cd9df556acd")
    public ActivityNode getElement() {
        return this.elt;
    }

    @objid ("f8c5d88e-71e3-42c2-b360-cda4c1c7cb8a")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("8be9e1b2-94c8-4544-9035-128d2ca5270a")
    protected UML2SetUp(ActivityNode elt) {
        this.elt = elt;
    }

    @objid ("f4de3ad0-deac-44e6-8597-cd122260702a")
    public static final class MdaTypes {
        @objid ("623d0b86-3470-4deb-b749-36892035d750")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("8e962e90-78a7-4d19-83b2-3321100da6e6")
        private static Stereotype MDAASSOCDEP;

        @objid ("20645724-28e6-4ccf-935a-0fda5b7242cc")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("50525cbe-fbe3-42d6-affa-195eeb030ff7")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "86eada10-32d9-11e0-91f3-0027103f347c");
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
