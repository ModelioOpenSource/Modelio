/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.elementimport;

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
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link ElementImport} with << create >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1ace0953-e65f-4688-9309-8b3b6ed39c7c")
public class Create {
    @objid ("99dab7fd-0abb-4586-9f49-62a6eeafa7b8")
    public static final String STEREOTYPE_NAME = "create";

    /**
     * The underlying {@link ElementImport} represented by this proxy, never null.
     */
    @objid ("e1e84ff1-9587-4463-81da-e5c870582586")
    protected final ElementImport elt;

    /**
     * Tells whether a {@link Create proxy} can be instantiated from a {@link MObject} checking it is a {@link ElementImport} stereotyped << create >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("b00984a4-9c39-4e4e-941f-9ec5116e2af6")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ElementImport) && ((ElementImport) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Create.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ElementImport} stereotyped << create >> then instantiate a {@link Create} proxy.
     * 
     * @return a {@link Create} proxy on the created {@link ElementImport}.
     */
    @objid ("a1496f8c-5722-4a03-bd26-63340573d9cd")
    public static Create create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("ElementImport");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Create.STEREOTYPE_NAME);
        return Create.instantiate((ElementImport)e);
    }

    /**
     * Tries to instantiate a {@link Create} proxy from a {@link ElementImport} stereotyped << create >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ElementImport
     * @return a {@link Create} proxy or <i>null</i>.
     */
    @objid ("d791ee31-9627-47fd-a371-18899cb84f97")
    public static Create instantiate(ElementImport obj) {
        return Create.canInstantiate(obj) ? new Create(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Create} proxy from a {@link ElementImport} stereotyped << create >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ElementImport}
     * @return a {@link Create} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("6d53bc6c-4b3d-496c-8f7f-cf172f84f04b")
    public static Create safeInstantiate(ElementImport obj) throws IllegalArgumentException {
        if (Create.canInstantiate(obj))
        	return new Create(obj);
        else
        	throw new IllegalArgumentException("Create: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("cb6eecab-beca-4738-bc14-097b981c268a")
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
        Create other = (Create) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ElementImport}. 
     * @return the ElementImport represented by this proxy, never null.
     */
    @objid ("16208643-e75e-4c71-b63c-23f9491356eb")
    public ElementImport getElement() {
        return this.elt;
    }

    @objid ("18d7383f-a0fb-4c4b-a19b-68eabbe86ddb")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("9ee75ec6-36ff-452a-991b-c8d1dd6434bf")
    protected Create(ElementImport elt) {
        this.elt = elt;
    }

    @objid ("97dedb37-d9c0-46d3-8bc3-639e2cfc8c0e")
    public static final class MdaTypes {
        @objid ("91f3cba9-d9c0-48a9-b6c0-109f89a716b8")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("0705ea92-eff0-44f5-8a04-d2a42cf013b4")
        private static Stereotype MDAASSOCDEP;

        @objid ("8ad2c9f9-3376-44c1-af53-c9a58b139659")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7566af5d-ea83-4e57-9284-e1e15e67cd3c")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-0202-0000-000000000000");
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
