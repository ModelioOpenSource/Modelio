/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.app.ui.cert;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import javax.security.auth.x500.X500Principal;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.modelio.app.ui.plugin.AppUi;
import org.modelio.ui.dialog.ModelioDialog;

/**
 * Dialog that display invalid certificates errors.
 * <p>
 * The dialog makes an analysis of the error, the certificate, displays the stack trace. It allows the user to either:
 * <ul>
 * <li>cancel the access,
 * <li>trust the certificate for Modelio's runtime lifetime
 * <li>trust the certificate definitively
 * </ul>
 */
@objid ("6785e103-2c70-4e3a-bb8b-3b381ce8c7c7")
public class UntrustedServerDialog extends ModelioDialog {
    /**
     * Code returned by {@link #open()} to trust definitively the certificate.
     */
    @objid ("bcda842e-0976-4c3c-8930-a0765fba0754")
    public static final int TRUST_ALWAYS_ID = 21;

    /**
     * Code returned by {@link #open()} to trust the certificate for the running session.
     */
    @objid ("355b5bd6-560d-4c61-9270-653469afb341")
    public static final int TRUST_ONCE_ID = 20;

    @objid ("9d5e0176-18d8-4628-a7e9-4cbe524bd983")
    private Throwable error;

    @objid ("c4ac3dab-1dea-4498-9b26-78e9f174306b")
    private URI uri;

    @objid ("cab86222-4f98-4ccd-96c6-dc4de6a171b7")
    private X509Certificate[] chain;

    /**
     * @param parentShell a SWT shell
     * @param uri the accessed URI
     * @param chain the invalid certificate chain
     * @param error the error
     */
    @objid ("ade4fc4d-e4ae-47f7-9b7d-498fdab65c9f")
    public UntrustedServerDialog(Shell parentShell, URI uri, X509Certificate[] chain, Throwable error) {
        super(parentShell);
        this.error = error;
        this.uri = uri;
        this.chain = chain;
    }

    @objid ("af3961dc-a96e-465d-926f-70ee31ef257c")
    @Override
    public Control createContentArea(Composite parent) {
        final FormToolkit tk = new FormToolkit(parent.getDisplay());
        
        parent.addDisposeListener(new DisposeListener() {
            @Override
            public void widgetDisposed(DisposeEvent e) {
                tk.dispose();
            }
        });
        
        ScrolledForm scrolledForm = tk.createScrolledForm(parent);
        GridDataFactory.defaultsFor(scrolledForm).align(SWT.FILL, SWT.FILL).grab(true, false).hint(600, 500).applyTo(scrolledForm);
        
        final Composite body = scrolledForm.getBody();
        body.setBackground(tk.getColors().getBackground());
        // Workaround for bugged TableWrapLayout: use a simple grid layout
        body.setLayout(new GridLayout());
        GridDataFactory wrappedLayoutData = GridDataFactory.fillDefaults().grab(true, false).hint(50, SWT.DEFAULT);
        
        Composite area = body;
        
        Section msgArea = tk.createSection(area, ExpandableComposite.TITLE_BAR | ExpandableComposite.EXPANDED);
        msgArea.setText(AppUi.I18N.getMessage("UntrustedServerDialog.msgarea.title"));
        
        String serverName = this.uri.getHost();
        String text = AppUi.I18N.getMessage("UntrustedServerDialog.message", serverName);
        FormText ft = tk.createFormText(msgArea, false);
        ft.setText(text, true, true);
        msgArea.setClient(ft);
        msgArea.setExpanded(true);
        wrappedLayoutData.applyTo(msgArea);
        
        // Technical error details
        Section techArea = tk.createSection(area, ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE);
        techArea.setText(AppUi.I18N.getMessage("UntrustedServerDialog.techarea.title"));
        wrappedLayoutData.applyTo(techArea);
        
        Text widget = tk.createText(techArea, buildTechDetails(), SWT.MULTI | SWT.WRAP);
        widget.setEditable(false);
        widget.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_DARK_RED));
        techArea.setClient(widget);
        techArea.setExpanded(true);
        
        // Exception stack trace
        Section excArea = tk.createSection(area, ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE);
        excArea.setText(AppUi.I18N.getMessage("UntrustedServerDialog.exceptionarea.title"));
        wrappedLayoutData.applyTo(excArea);
        
        StringWriter s = new StringWriter();
        this.error.printStackTrace(new PrintWriter(s));
        widget = tk.createText(excArea, s.toString(), SWT.MULTI | SWT.WRAP);
        widget.setEditable(false);
        excArea.setClient(widget);
        
        // Certificate area
        ExpandableComposite certArea = tk.createSection(area, ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE);
        certArea.setText(AppUi.I18N.getMessage("UntrustedServerDialog.certarea.title"));
        wrappedLayoutData.applyTo(certArea);
        
        widget = tk.createText(certArea, this.chain[0].toString(), SWT.MULTI | SWT.WRAP);
        widget.setEditable(false);
        certArea.setClient(widget);
        
        // Understand area
        createUnderstandSection(tk, area, serverName);
        return scrolledForm;
    }

    @objid ("6fa7cb53-6b03-4f69-92e1-b18829755434")
    private void createUnderstandSection(final FormToolkit tk, Composite area, String serverName) {
        GridDataFactory wrappedLayoutData = GridDataFactory.fillDefaults().grab(true, false).hint(50, SWT.DEFAULT);
        
        ExpandableComposite understandArea = tk.createSection(area, ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE);
        understandArea.setText(AppUi.I18N.getMessage("UntrustedServerDialog.understandarea.title"));
        wrappedLayoutData.applyTo(understandArea);
        
        Composite compo = tk.createComposite(understandArea, SWT.WRAP);
        compo.setLayout(new GridLayout());
        String text = AppUi.I18N.getMessage("UntrustedServerDialog.understandarea.message", serverName);
        
        FormText ft = tk.createFormText(compo, false);
        ft.setText(text, true, true);
        understandArea.setClient(compo);
        wrappedLayoutData.applyTo(ft);
        
        Composite toolbar = tk.createComposite(compo, SWT.WRAP);
        wrappedLayoutData.applyTo(toolbar);
        
        GridLayoutFactory.swtDefaults().numColumns(1).equalWidth(false).applyTo(toolbar);
        GridDataFactory.swtDefaults().align(SWT.FILL, SWT.TOP).grab(true, false).applyTo(new Label(toolbar, 0));
        
        createButton(toolbar, UntrustedServerDialog.TRUST_ONCE_ID, AppUi.I18N.getMessage("UntrustedServerDialog.button.TrustOnce"), false);
        createButton(toolbar, UntrustedServerDialog.TRUST_ALWAYS_ID, AppUi.I18N.getMessage("UntrustedServerDialog.button.TrustAlways"), false);
    }

    @objid ("22bb1caa-15ca-49fb-a15b-ff238c06654e")
    private String buildTechDetails() {
        StringBuilder s = new StringBuilder();
        s.append(this.error.getLocalizedMessage());
        
        s.append("\n\n");
        s.append(AppUi.I18N.getMessage("UntrustedServerDialog.diag.title"));
        s.append("\n");
        String certFor = this.uri.getHost();
        for (int i = 0; i < this.chain.length; i++) {
            X509Certificate cert = this.chain[i];
            X509Certificate nextCert = i + 1 < this.chain.length ? this.chain[i + 1] : null;
        
            String subjectName = getName(cert.getSubjectX500Principal());
        
            s.append(" * ");
            s.append(AppUi.I18N.getMessage("UntrustedServerDialog.diag.title.cert", subjectName));
            s.append("\n");
        
            for (String entry : getCertificateDiagnostic(cert, certFor, nextCert)) {
                s.append("    - ");
                s.append(entry);
                s.append("\n");
            }
        
            certFor = subjectName;
        }
        return s.toString();
    }

    @objid ("1c34d5fd-00c6-4131-91ee-8a90ec82462c")
    @Override
    public void addButtonsInButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, true);
    }

    @objid ("5fc7adfc-1205-4923-9005-9948cb4c5daa")
    @Override
    protected void buttonPressed(int buttonId) {
        switch (buttonId) {
        case TRUST_ONCE_ID:
        case TRUST_ALWAYS_ID:
        case IDialogConstants.CANCEL_ID:
            setReturnCode(buttonId);
            close();
            break;
        default:
            super.buttonPressed(buttonId);
        }
    }

    @objid ("3fdadaa1-2b09-4ede-ac0a-9361a887c363")
    @Override
    public void init() {
        getShell().setText(AppUi.I18N.getMessage("UntrustedServerDialog.title"));
        setTitle(AppUi.I18N.getMessage("UntrustedServerDialog.header", this.uri.getHost(), this.error.getLocalizedMessage()));
    }

    @objid ("b857902a-c6a1-4352-a817-c54257455fe8")
    private static Collection<String> getCertificateDiagnostic(X509Certificate cert, String realHostName, X509Certificate nextCert) {
        Collection<String> ret = new ArrayList<>();
        
        if (nextCert != null) {
            try {
                cert.verify(nextCert.getPublicKey());
                ret.add(AppUi.I18N.getMessage("UntrustedServerDialog.diag.selfsigned"));
            } catch (InvalidKeyException | CertificateException | NoSuchAlgorithmException | NoSuchProviderException
                    | SignatureException e1) {
                ret.add(e1.getLocalizedMessage());
            }
        } else {
            // Test if the certificate is self signed
            try {
                cert.verify(cert.getPublicKey());
                ret.add(AppUi.I18N.getMessage("UntrustedServerDialog.diag.selfsigned"));
            } catch (@SuppressWarnings ("unused") SignatureException e1) {
                // Not self signed
                final X500Principal issuer = cert.getIssuerX500Principal();
                if (issuer == null) {
                    ret.add(AppUi.I18N.getMessage("UntrustedServerDialog.diag.noissuer"));
                } else {
                    ret.add(AppUi.I18N.getMessage("UntrustedServerDialog.diag.noissuercertificate", getName(issuer)));
                }
            } catch (InvalidKeyException | CertificateException | NoSuchAlgorithmException | NoSuchProviderException e1) {
                // ignore
                ret.add(e1.getLocalizedMessage());
            }
        }
        
        Date time = new Date(System.currentTimeMillis());
        if (time.before(cert.getNotBefore())) {
            // The certificate is not yet valid.
        
            ret.add(AppUi.I18N.getMessage("UntrustedServerDialog.diag.notyetvalid", DateFormat.getDateTimeInstance().format(cert.getNotBefore())));
        }
        if (time.after(cert.getNotAfter())) {
            // The certificate has expired.
            ret.add(AppUi.I18N.getMessage("UntrustedServerDialog.diag.expired", DateFormat.getDateTimeInstance().format(cert.getNotAfter())));
        }
        String certHostName = getName(cert.getSubjectX500Principal());
        
        if (!realHostName.equals(certHostName)) {
            try {
                Collection<List<?>> altNames = cert.getSubjectAlternativeNames();
                if (altNames != null) {
                    for (Object nameList : altNames) {
                        if (nameList instanceof Collection && ((Collection<?>) nameList).size() >= 2) {
                            Object[] name = ((Collection<?>) nameList).toArray();
                            Object type = name[0];
                            Object host = name[1];
                            if (type instanceof Integer && host instanceof String) {
                                if (((Integer) type).intValue() == 2 && host.equals(realHostName)) {
                                    // The certificate hostname does match
                                    return ret;
                                }
                            }
                        }
                    }
                }
            } catch (@SuppressWarnings ("unused") CertificateParsingException e) {
                // ignore
            }
            // The certificate hostname does not match
            ret.add(AppUi.I18N.getMessage("UntrustedServerDialog.diag.badhostname"));
        }
        return ret;
    }

    @objid ("89555707-2268-4175-ab19-cfbb212c0f98")
    private static String getName(X500Principal pr) {
        String dn = pr.getName();
        LdapName ldapDN;
        try {
            ldapDN = new LdapName(dn);
            for (Rdn r : ldapDN.getRdns()) {
                final Attribute attribute = r.toAttributes().get("cn");
                if (attribute != null) {
                    return (String) attribute.get();
                }
            }
            return dn;
        } catch (NamingException e) {
            return e.toString() + "( from " + pr.toString() + ")";
        }
    }

}
