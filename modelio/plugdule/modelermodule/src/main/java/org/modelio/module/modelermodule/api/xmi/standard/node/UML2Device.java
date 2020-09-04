/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Node} with << UML2Device >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("35943c26-6bcb-4f3a-8cc4-05428af13dcf")
public class UML2Device {
    @objid ("f10a8344-6aad-4762-8e18-95adba9d8b98")
    public static final String STEREOTYPE_NAME = "UML2Device";

    /**
     * The underlying {@link Node} represented by this proxy, never null.
     */
    @objid ("b6f3dc41-b31c-48fc-b484-55233a62453d")
    protected final Node elt;

    /**
     * Tells whether a {@link UML2Device proxy} can be instantiated from a {@link MObject} checking it is a {@link Node} stereotyped << UML2Device >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("210b6b42-2db4-4dc8-9ab9-6467a98446c3")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Node) && ((Node) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Device.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Node} stereotyped << UML2Device >> then instantiate a {@link UML2Device} proxy.
     * 
     * @return a {@link UML2Device} proxy on the created {@link Node}.
     */
    @objid ("3f4357cd-bcc8-4c61-9db4-6807703fbc11")
    public static UML2Device create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Node");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Device.STEREOTYPE_NAME);
        return UML2Device.instantiate((Node)e);
    }

    /**
     * Tries to instantiate a {@link UML2Device} proxy from a {@link Node} stereotyped << UML2Device >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Node
     * @return a {@link UML2Device} proxy or <i>null</i>.
     */
    @objid ("98a4c72a-665c-47d6-8674-f302a801b160")
    public static UML2Device instantiate(Node obj) {
        return UML2Device.canInstantiate(obj) ? new UML2Device(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Device} proxy from a {@link Node} stereotyped << UML2Device >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Node}
     * @return a {@link UML2Device} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("dc49aa92-d29e-431d-b51e-e2689c2cae46")
    public static UML2Device safeInstantiate(Node obj) throws IllegalArgumentException {
        if (UML2Device.canInstantiate(obj))
        	return new UML2Device(obj);
        else
        	throw new IllegalArgumentException("UML2Device: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("9fda20ab-8efa-4bbc-a41a-be8f9a35ccdc")
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
        UML2Device other = (UML2Device) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Node}. 
     * @return the Node represented by this proxy, never null.
     */
    @objid ("440950b1-89e8-4bde-b104-2fe6e414e1fb")
    public Node getElement() {
        return this.elt;
    }

    @objid ("3fbf10fb-327b-4f99-a0cb-53c4bab3c77c")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("8a62bbd3-9ed5-4ec0-8d4e-88bcddfe9685")
    protected UML2Device(Node elt) {
        this.elt = elt;
    }

    @objid ("523ea551-51fc-4d7a-8cc8-4bf7ca4c5370")
    public static final class MdaTypes {
        @objid ("ef93d327-afd4-4ccd-810e-8079da4b4c8f")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ed2742bc-67a5-4587-9be2-1562a844315e")
        private static Stereotype MDAASSOCDEP;

        @objid ("8755e35a-bb3e-4b4c-8e33-afb4d3aafa8b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("6e9da596-a024-4b8a-a6ce-3cbcb441e035")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "6f3c6234-52b8-11df-a320-001302895b2b");
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
