package cucumber.eclipse.editor.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import cucumber.eclipse.editor.Activator;
import cucumber.eclipse.editor.preferences.ICucumberPreferenceConstants.CucumberIndentationStyle;
import cucumber.eclipse.steps.integration.StepPreferences;

public class CucumberPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public CucumberPreferencePage() {
		super(FLAT);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}
	
	@Override
	protected void createFieldEditors() {
				
		Composite parent = getFieldEditorParent();
		
		CLabel label = new CLabel(parent, SWT.NULL);
		label.setText(getString("Plugin Settings"));
		label.setImage(getImage("icons/cukes.gif"));

		
		addField(new BooleanFieldEditor(
			StepPreferences.PREF_CHECK_STEP_DEFINITIONS,
			getString("&Enable step definitions glue detection"), parent));
			
		//#239:Only match step implementation in same package as feature file	
		addField(new BooleanFieldEditor(
			StepPreferences.PREF_GLUE_ONLY_IN_SAME_LOCATION,
			getString("&Glue only gherkins and step definitions files in the same location"), getFieldEditorParent()));
		
		
		parent = getFieldEditorParent();
		
		label = new CLabel(parent, SWT.NULL);
		label.setText(getString("Gherkin Formatting"));
		label.setImage(getImage("icons/cukes.gif"));
		
		addField(new BooleanFieldEditor(
			ICucumberPreferenceConstants.PREF_FORMAT_RIGHT_ALIGN_NUMERIC_VALUES_IN_TABLES,
			getString("&Right-align numeric values in tables"), parent));
		
		addField(new BooleanFieldEditor(
			ICucumberPreferenceConstants.PREF_FORMAT_CENTER_STEPS,
			getString("&Center Steps"), getFieldEditorParent()));

		addField(new BooleanFieldEditor(
			ICucumberPreferenceConstants.PREF_FORMAT_PRESERVE_BLANK_LINE_BETWEEN_STEPS,
			getString("&Preserve blank lines between steps"), getFieldEditorParent()));
		
		addField(new ComboFieldEditor(ICucumberPreferenceConstants.PREF_INDENTATION_STYLE, 
			getString("&Indentation Style:"), CucumberIndentationStyle.getLabelsAndValues(), 
			getFieldEditorParent()));
	}

	public static Image getImage(String imagePath) {
		LocalResourceManager manager = new LocalResourceManager(JFaceResources.getResources());
		ImageDescriptor imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, imagePath);
		Image image = manager.createImage(imageDescriptor);
		return image;
	}

	public static String getString(String key) {
		// TODO: load strings via .messages file from resource bundle...
		return key;
	}

	@Override
	public void init(IWorkbench arg0) {
		// nothing to init here...
	}

}
