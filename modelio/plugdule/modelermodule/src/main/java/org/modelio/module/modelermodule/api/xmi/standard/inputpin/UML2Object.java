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
 * Proxy class to handle a {@link InputPin} with << UML2Object >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("4d9f0770-42a1-41ca-b31a-a5051fa170da")
public class UML2Object {
    @objid ("d848b134-1dc5-4a66-a007-9238c2d0e623")
    public static final String STEREOTYPE_NAME = "UML2Object";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("b70d24e6-2a79-4631-937a-a76575801125")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Object proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Object >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("6ea0ab9b-4458-4176-975b-654999ff1c31")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Object.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Object >> then instantiate a {@link UML2Object} proxy.
     * 
     * @return a {@link UML2Object} proxy on the created {@link InputPin}.
     */
    @objid ("2dd40369-2e45-43a0-a417-9ba0edcf4931")
    public static UML2Object create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Object.STEREOTYPE_NAME);
        return UML2Object.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Object} proxy from a {@link InputPin} stereotyped << UML2Object >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2Object} proxy or <i>null</i>.
     */
    @objid ("6c8b576d-e848-43e9-9932-0828d68c1a7c")
    public static UML2Object instantiate(InputPin obj) {
        return UML2Object.canInstantiate(obj) ? new UML2Object(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Object} proxy from a {@link InputPin} stereotyped << UML2Object >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2Object} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("706ea86e-bac9-4f57-b1a2-63646e769c18")
    public static UML2Object safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Object.canInstantiate(obj))
        	return new UML2Object(obj);
        else
        	throw new IllegalArgumentException("UML2Object: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("58f63b4e-e377-4eb6-930d-67231503c0eb")
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
        UML2Object other = (UML2Object) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("234d20cd-e362-425e-b3cc-2e3854bef214")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("a7558196-7491-427f-85c8-90a710d1f3d3")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("39522cd6-e1f2-405f-9868-1aeba3df663f")
    protected UML2Object(InputPin elt) {
        this.elt = elt;
    }

    @objid ("a5f36406-a8bc-4203-87a4-7ada95b3bbc6")
    public static final class MdaTypes {
        @objid ("efb76ec8-6685-485b-ba9d-6385d06aeaae")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("f64cb3ce-9cb3-4f64-ac20-380bd286fb6e")
        private static Stereotype MDAASSOCDEP;

        @objid ("8350bb07-5ab1-4307-9904-ed55f60a2883")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("d0d98664-805f-45ba-9129-e2a3053a4f3d")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "f82bed81-afcc-41fc-b014-b9ce92bb5377");
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
