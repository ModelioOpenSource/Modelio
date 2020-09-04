/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.outputpin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
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
 * Proxy class to handle a {@link OutputPin} with << UML2Result >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("44487931-3470-4987-9272-bb45a6d23c7e")
public class UML2Result {
    @objid ("b79118f3-65ed-44a7-b0a6-30150175b579")
    public static final String STEREOTYPE_NAME = "UML2Result";

    /**
     * The underlying {@link OutputPin} represented by this proxy, never null.
     */
    @objid ("922c4d3b-4249-4c70-92fd-d4dca544a0f1")
    protected final OutputPin elt;

    /**
     * Tells whether a {@link UML2Result proxy} can be instantiated from a {@link MObject} checking it is a {@link OutputPin} stereotyped << UML2Result >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("b912bb79-99be-46d7-b21f-1b8c45b4989d")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OutputPin) && ((OutputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Result.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OutputPin} stereotyped << UML2Result >> then instantiate a {@link UML2Result} proxy.
     * 
     * @return a {@link UML2Result} proxy on the created {@link OutputPin}.
     */
    @objid ("ea3b593b-6f41-4b37-9a86-ab4d6e6dc98a")
    public static UML2Result create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OutputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Result.STEREOTYPE_NAME);
        return UML2Result.instantiate((OutputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Result} proxy from a {@link OutputPin} stereotyped << UML2Result >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OutputPin
     * @return a {@link UML2Result} proxy or <i>null</i>.
     */
    @objid ("f64468cf-01d4-47c1-b68e-388420195e6b")
    public static UML2Result instantiate(OutputPin obj) {
        return UML2Result.canInstantiate(obj) ? new UML2Result(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Result} proxy from a {@link OutputPin} stereotyped << UML2Result >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OutputPin}
     * @return a {@link UML2Result} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("d8526579-5805-4034-8a3b-c66c103b5d49")
    public static UML2Result safeInstantiate(OutputPin obj) throws IllegalArgumentException {
        if (UML2Result.canInstantiate(obj))
        	return new UML2Result(obj);
        else
        	throw new IllegalArgumentException("UML2Result: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c696d5e7-15aa-4077-ac9a-58c8cfa5ab58")
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
        UML2Result other = (UML2Result) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OutputPin}. 
     * @return the OutputPin represented by this proxy, never null.
     */
    @objid ("f030c149-cd83-4034-a1e6-560202019102")
    public OutputPin getElement() {
        return this.elt;
    }

    @objid ("93805f88-8a03-4af8-947e-003236e4bbc1")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("c112cb07-b7ad-464b-b11f-68238fda8908")
    protected UML2Result(OutputPin elt) {
        this.elt = elt;
    }

    @objid ("ad42c1ae-a610-4d17-8bc4-7ff3c78480be")
    public static final class MdaTypes {
        @objid ("bc6f298b-dd58-4f05-b88c-6398062076f3")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("4e33a520-1bd8-4cd0-9236-aca95c656878")
        private static Stereotype MDAASSOCDEP;

        @objid ("a9f22f26-6650-4635-9304-99d3798aaee8")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("ae4af05f-8635-40b9-b91f-084d9a0d1154")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "8914ba10-818c-4439-8e2b-89671c2288bc");
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
