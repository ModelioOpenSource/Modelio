/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.inputpin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
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
 * Proxy class to handle a {@link InputPin} with << UML2Collection >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("f516af7e-6ec9-4d1a-97e6-56bb5fa55e8c")
public class UML2Collection {
    @objid ("7a4022a3-e65a-4ca4-b5c7-e3aeeae12c09")
    public static final String STEREOTYPE_NAME = "UML2Collection";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("904401ac-04d0-4e87-ad77-42012b84bb64")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Collection proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Collection >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("c450fc7c-84cd-4835-96c6-0ad202930bb4")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Collection.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Collection >> then instantiate a {@link UML2Collection} proxy.
     * 
     * @return a {@link UML2Collection} proxy on the created {@link InputPin}.
     */
    @objid ("bb68812b-6992-49b8-88ed-071b8673480c")
    public static UML2Collection create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Collection.STEREOTYPE_NAME);
        return UML2Collection.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Collection} proxy from a {@link InputPin} stereotyped << UML2Collection >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2Collection} proxy or <i>null</i>.
     */
    @objid ("4f5970d9-3fc8-4d2e-a4b3-e9660c2e2a8a")
    public static UML2Collection instantiate(InputPin obj) {
        return UML2Collection.canInstantiate(obj) ? new UML2Collection(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Collection} proxy from a {@link InputPin} stereotyped << UML2Collection >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2Collection} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("92df0cac-b56a-43ba-a769-8f487dec9a3c")
    public static UML2Collection safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Collection.canInstantiate(obj))
        	return new UML2Collection(obj);
        else
        	throw new IllegalArgumentException("UML2Collection: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("731d15fa-472a-4e45-b4fd-7707370ceefe")
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
        UML2Collection other = (UML2Collection) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("f598302a-1c4f-4435-a08a-2ee6802e4f4d")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("64ef445a-a849-49b3-8f59-9c4f94f33adc")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("c3db7137-48f2-4c67-93d3-f5ae920dc15c")
    protected UML2Collection(InputPin elt) {
        this.elt = elt;
    }

    @objid ("476654d1-38c0-4f14-b5ad-11e07fce9988")
    public static final class MdaTypes {
        @objid ("55305816-2dfc-49ca-b9be-670601aca570")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b63361b2-74ee-42bc-86c5-8b7d88d96654")
        private static Stereotype MDAASSOCDEP;

        @objid ("9f449d30-3281-4b77-8193-9104f4b9119b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("0121366d-0d59-487f-b02a-75ceb566d126")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "3e7476cd-bea2-4e73-a1c3-625c341464cd");
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
