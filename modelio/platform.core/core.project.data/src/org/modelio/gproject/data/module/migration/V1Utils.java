/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.gproject.data.module.migration;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.module.jaxbv1.JxbClasspath;
import org.modelio.gproject.data.module.jaxbv1.JxbContextualCommand.Contribution;
import org.modelio.gproject.data.module.jaxbv1.JxbDocpath;
import org.modelio.gproject.data.module.jaxbv1.JxbModule.Dependencies.Optional;
import org.modelio.gproject.data.module.jaxbv1.JxbModule.Dependencies.Required;
import org.modelio.gproject.data.module.jaxbv1.JxbModule.Dependencies;
import org.modelio.gproject.data.module.jaxbv1.JxbModule.Gui.Command;
import org.modelio.gproject.data.module.jaxbv1.JxbModule.Gui.CustomizedDiagram.Palette;
import org.modelio.gproject.data.module.jaxbv1.JxbModule.Gui.CustomizedDiagram.Style;
import org.modelio.gproject.data.module.jaxbv1.JxbModule.Gui.CustomizedDiagram;
import org.modelio.gproject.data.module.jaxbv1.JxbModule.Gui.ElementCreationCommand;
import org.modelio.gproject.data.module.jaxbv1.JxbModule.Gui.PropertyPage;
import org.modelio.gproject.data.module.jaxbv1.JxbModule.Gui;
import org.modelio.gproject.data.module.jaxbv1.JxbModule.JxbParameter.JxbEnumeration;
import org.modelio.gproject.data.module.jaxbv1.JxbModule.JxbParameter;
import org.modelio.gproject.data.module.jaxbv1.JxbModule.JxbProfile.JxbAnonymousStereotype;
import org.modelio.gproject.data.module.jaxbv1.JxbModule.JxbProfile.JxbStereotype;
import org.modelio.gproject.data.module.jaxbv1.JxbModule.JxbProfile;
import org.modelio.gproject.data.module.jaxbv1.JxbModule;

@objid ("80818a5e-b506-4d84-9c5b-5f78df3f3b6d")
class V1Utils {
    @objid ("0fe76891-aa4b-486b-bd5b-95d9962a784a")
    public static Gui getGui(JxbModule module) {
        for (Object obj : module.getParameterOrProfileOrGui()) {
            if (obj instanceof Gui) {
                return (Gui) obj;
            }
        }
        return null;
    }

    @objid ("541a675e-60d0-4bf2-bbcb-e230398d6a58")
    public static List<Command> getCommands(Gui gui) {
        List<Command> commands = new ArrayList<>();
        for (Object obj : gui.getPropertyPageOrCommandOrElementCreationCommand()) {
            if (obj instanceof Command) {
                commands.add((Command) obj);
            }
        }
        return commands;
    }

    @objid ("3bea87e0-50ec-4833-8e6e-9ebbe02f9ae0")
    public static List<ElementCreationCommand> getCreationCommands(org.modelio.gproject.data.module.jaxbv1.JxbModule.Gui gui) {
        List<ElementCreationCommand> commands = new ArrayList<>();
        for (Object obj : gui.getPropertyPageOrCommandOrElementCreationCommand()) {
            if (obj instanceof ElementCreationCommand) {
                commands.add((ElementCreationCommand) obj);
            }
        }
        return commands;
    }

    @objid ("05280b7c-fe72-455e-ae2e-3bb006958e65")
    public static List<PropertyPage> getPropertyPages(Gui gui) {
        List<PropertyPage> propertyPages = new ArrayList<>();
        for (Object obj : gui.getPropertyPageOrCommandOrElementCreationCommand()) {
            if (obj instanceof PropertyPage) {
                propertyPages.add((PropertyPage) obj);
            }
        }
        return propertyPages;
    }

    @objid ("eaed6ff0-1b05-4885-b30b-cff50f880f65")
    public static Contribution getContribution(Command command) {
        for (Object obj : command.getScopeOrHandlerOrContribution()) {
            if (obj instanceof Contribution) {
                return (Contribution) obj;
            }
        }
        return null;
    }

    @objid ("b4a22b5e-cfb4-4597-aa80-afbf97814e46")
    public static Contribution getContribution(ElementCreationCommand command) {
        for (Object obj : command.getScopeOrHandlerOrContribution()) {
            if (obj instanceof Contribution) {
                return (Contribution) obj;
            }
        }
        return null;
    }

    @objid ("bbc42001-9870-4909-8ccc-9217f9ddc5aa")
    public static List<JxbParameter> getParameters(JxbModule module) {
        List<JxbParameter> parameters = new ArrayList<>();
        for (Object obj : module.getParameterOrProfileOrGui()) {
            if (obj instanceof org.modelio.gproject.data.module.jaxbv1.JxbModule.JxbParameter) {
                parameters.add((JxbParameter) obj);
            }
        }
        return parameters;
    }

    @objid ("888a1d3e-e44e-486b-ad68-fe62076ac291")
    public static JxbEnumeration getParameterEnumeration(JxbParameter param) {
        for (Object obj : param.getEnumerationOrDescription()) {
            if (obj instanceof JxbEnumeration) {
                return (JxbEnumeration) obj;
            }
        }
        return null;
    }

    @objid ("415dc262-a443-46f1-b755-424994aed630")
    public static String getParameterDescription(JxbParameter param) {
        for (Object obj : param.getEnumerationOrDescription()) {
            if (obj instanceof String) {
                return (String) obj;
            }
        }
        return null;
    }

    @objid ("e2790eb0-444f-4d38-9caa-2fa265b6fb7b")
    public static JxbClasspath getClassPath(JxbModule module) {
        for (Object obj : module.getParameterOrProfileOrGui()) {
            if (obj instanceof JxbClasspath) {
                return (JxbClasspath) obj;
            }
        }
        return null;
    }

    @objid ("6f02ef14-04a4-4839-a8e4-b1eca2283ac2")
    public static JxbDocpath getDocPath(JxbModule module) {
        for (Object obj : module.getParameterOrProfileOrGui()) {
            if (obj instanceof JxbDocpath) {
                return (JxbDocpath) obj;
            }
        }
        return null;
    }

    @objid ("23f3f137-35df-4fe6-b00f-29dc43f26161")
    public static List<Optional> getOptionalDependencies(JxbModule module) {
        List<Optional> deps = new ArrayList<>();
        
        for (Object obj : module.getParameterOrProfileOrGui()) {
            if (obj instanceof JxbModule.Dependencies) {
                for (Object pobj : ((Dependencies) obj).getRequiredOrOptionalOrRamc()) {
                    if (pobj instanceof Optional) {
                        deps.add((Optional) pobj);
                    }
                }
                break;
            }
        }
        return deps;
    }

    @objid ("2a6c405d-b5c5-4708-a573-ae853fab0cd2")
    public static List<Required> getRequiredDependencies(JxbModule module) {
        List<Required> deps = new ArrayList<>();
        
        for (Object obj : module.getParameterOrProfileOrGui()) {
            if (obj instanceof JxbModule.Dependencies) {
                for (Object pobj : ((Dependencies) obj).getRequiredOrOptionalOrRamc()) {
                    if (pobj instanceof Required) {
                        deps.add((Required) pobj);
        
                    }
                }
                break;
            }
        }
        return deps;
    }

    @objid ("b799b494-b09b-45c7-b51b-199b92d0e5c1")
    public static List<JxbProfile> getProfiles(JxbModule module) {
        List<JxbProfile> profiles = new ArrayList<>();
        for (Object obj : module.getParameterOrProfileOrGui()) {
            if (obj instanceof JxbProfile) {
                profiles.add((JxbProfile) obj);
            }
        }
        return profiles;
    }

    @objid ("4115cef6-0c72-498d-84fc-08cb651c4767")
    public static List<JxbStereotype> getStereotypes(JxbProfile profile) {
        List<JxbStereotype> stereotypes = new ArrayList<>();
        for (Object obj : profile.getStereotypeOrAnonymousStereotype()) {
            if (obj instanceof JxbStereotype) {
                stereotypes.add((JxbStereotype) obj);
            }
        }
        return stereotypes;
    }

    @objid ("9f8cd8c1-9fd5-4e62-b315-a79c0b97bd97")
    public static List<JxbAnonymousStereotype> getAnonymousStereotypes(JxbProfile profile) {
        List<JxbAnonymousStereotype> stereotypes = new ArrayList<>();
        for (Object obj : profile.getStereotypeOrAnonymousStereotype()) {
            if (obj instanceof JxbAnonymousStereotype) {
                stereotypes.add((JxbAnonymousStereotype) obj);
            }
        }
        return stereotypes;
    }

    @objid ("d873ef95-33aa-4643-b5ac-b0b234006567")
    public static List<CustomizedDiagram> getCustomizedDiagrams(Gui gui) {
        List<CustomizedDiagram> diagrams = new ArrayList<>();
        
        for (Object obj : gui.getPropertyPageOrCommandOrElementCreationCommand()) {
            if (obj instanceof CustomizedDiagram) {
                diagrams.add((CustomizedDiagram) obj);
            }
        }
        return diagrams;
    }

    @objid ("0aaf765f-226a-4628-b0a5-47af81e757d4")
    public static Palette getDiagramPalette(CustomizedDiagram diagram) {
        for (Object obj : diagram.getPaletteOrStyle()) {
            if (obj instanceof Palette) {
                return (Palette) obj;
            }
        
        }
        return null;
    }

    @objid ("2f6e8733-85bf-4175-943d-5906fa0354c4")
    public static Style getDiagramStyle(CustomizedDiagram diagram) {
        for (Object obj : diagram.getPaletteOrStyle()) {
            if (obj instanceof Style) {
                return (Style) obj;
            }
        
        }
        return null;
    }

}
