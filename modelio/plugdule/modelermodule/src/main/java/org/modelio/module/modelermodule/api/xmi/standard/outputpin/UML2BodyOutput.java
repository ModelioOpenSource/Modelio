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
 * Proxy class to handle a {@link OutputPin} with << UML2BodyOutput >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("dbc1e7ca-f761-4429-b0c7-68a4c218ca5d")
public class UML2BodyOutput {
    @objid ("cb26c1ac-1d50-4d3b-ad0e-868b3619fe2c")
    public static final String STEREOTYPE_NAME = "UML2BodyOutput";

    /**
     * The underlying {@link OutputPin} represented by this proxy, never null.
     */
    @objid ("a8e464c0-adb1-4a41-88ff-7c7be701c8e4")
    protected final OutputPin elt;

    /**
     * Tells whether a {@link UML2BodyOutput proxy} can be instantiated from a {@link MObject} checking it is a {@link OutputPin} stereotyped << UML2BodyOutput >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("684fcd23-cd07-4285-a305-20ea56104187")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OutputPin) && ((OutputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2BodyOutput.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OutputPin} stereotyped << UML2BodyOutput >> then instantiate a {@link UML2BodyOutput} proxy.
     * 
     * @return a {@link UML2BodyOutput} proxy on the created {@link OutputPin}.
     */
    @objid ("8d489413-92ab-4205-983b-cec80373b128")
    public static UML2BodyOutput create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OutputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2BodyOutput.STEREOTYPE_NAME);
        return UML2BodyOutput.instantiate((OutputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2BodyOutput} proxy from a {@link OutputPin} stereotyped << UML2BodyOutput >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OutputPin
     * @return a {@link UML2BodyOutput} proxy or <i>null</i>.
     */
    @objid ("154b29cf-8af5-43c6-b18b-4c89d774e595")
    public static UML2BodyOutput instantiate(OutputPin obj) {
        return UML2BodyOutput.canInstantiate(obj) ? new UML2BodyOutput(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2BodyOutput} proxy from a {@link OutputPin} stereotyped << UML2BodyOutput >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OutputPin}
     * @return a {@link UML2BodyOutput} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("b66c8d84-88e9-489a-8c4f-1d4a36df1b40")
    public static UML2BodyOutput safeInstantiate(OutputPin obj) throws IllegalArgumentException {
        if (UML2BodyOutput.canInstantiate(obj))
        	return new UML2BodyOutput(obj);
        else
        	throw new IllegalArgumentException("UML2BodyOutput: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c4ab289d-4338-4ce7-a1c6-dddada34b4cb")
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
        UML2BodyOutput other = (UML2BodyOutput) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OutputPin}. 
     * @return the OutputPin represented by this proxy, never null.
     */
    @objid ("8f237412-4729-4a2d-af67-158f82bcf877")
    public OutputPin getElement() {
        return this.elt;
    }

    @objid ("065ce23c-a131-4374-bf39-d604b6610e66")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("2d32a822-f3e5-475f-bb49-3007c1b1bf74")
    protected UML2BodyOutput(OutputPin elt) {
        this.elt = elt;
    }

    @objid ("f8fb3049-bf2a-4fe1-9c58-ce020fedf5f3")
    public static final class MdaTypes {
        @objid ("0e740daf-464f-4232-9109-6d243b065854")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d2f6fd79-f072-4f57-a825-8b1a8a2c118f")
        private static Stereotype MDAASSOCDEP;

        @objid ("5c378163-3959-475b-b9b0-3513d3869137")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f168c2ec-2154-4372-9b52-faf65f9269f8")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "fdbc7d78-32c7-11e0-91f3-0027103f347c");
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
