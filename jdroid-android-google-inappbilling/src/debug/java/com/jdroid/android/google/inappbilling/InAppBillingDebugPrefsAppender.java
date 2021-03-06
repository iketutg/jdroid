package com.jdroid.android.google.inappbilling;

import android.app.Activity;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.PreferenceGroup;

import com.jdroid.android.debug.PreferencesAppender;
import com.jdroid.android.google.inappbilling.client.ProductType;
import com.jdroid.android.google.inappbilling.client.TestProductType;
import com.jdroid.java.collections.Lists;

import java.util.List;

public class InAppBillingDebugPrefsAppender extends PreferencesAppender {

	@Override
	public int getNameResId() {
		return R.string.jdroid_inAppBillingSettings;
	}
	
	@Override
	public void initPreferences(Activity activity, PreferenceGroup preferenceGroup) {
		
		CheckBoxPreference checkBoxPreference = new CheckBoxPreference(activity);
		checkBoxPreference.setKey(InAppBillingContext.MOCK_ENABLED);
		checkBoxPreference.setTitle(R.string.jdroid_inAppBillingMockEnabledTitle);
		checkBoxPreference.setSummary(R.string.jdroid_inAppBillingMockEnabledDescription);
		preferenceGroup.addPreference(checkBoxPreference);
		
		ListPreference preference = new ListPreference(activity);
		preference.setKey(InAppBillingContext.TEST_PRODUCT_IDS);
		preference.setTitle(R.string.jdroid_inAppBillingTestProductIdsTitle);
		preference.setDialogTitle(R.string.jdroid_inAppBillingTestProductIdsTitle);
		preference.setSummary(R.string.jdroid_inAppBillingTestProductIdsDescription);
		List<CharSequence> entries = Lists.newArrayList();
		for (TestProductType each : TestProductType.values()) {
			entries.add(each.name());
		}
		preference.setEntries(entries.toArray(new CharSequence[0]));
		preference.setEntryValues(entries.toArray(new CharSequence[0]));
		preferenceGroup.addPreference(preference);
		
		// Purchased products
		List<ProductType> purchasedProductTypes = InAppBillingAppModule.get().getInAppBillingContext().getPurchasedProductTypes();
		if (!purchasedProductTypes.isEmpty()) {
			preference = new ListPreference(activity);
			preference.setTitle(R.string.jdroid_inAppBillingPurchasedProductTypeTitle);
			preference.setDialogTitle(R.string.jdroid_inAppBillingPurchasedProductTypeTitle);
			preference.setSummary(R.string.jdroid_inAppBillingPurchasedProductTypeTitle);
			entries = Lists.newArrayList();
			for (ProductType each : InAppBillingAppModule.get().getInAppBillingContext().getPurchasedProductTypes()) {
				entries.add(each.getProductId());
			}
			preference.setEntries(entries.toArray(new CharSequence[0]));
			preference.setEntryValues(entries.toArray(new CharSequence[0]));
			preferenceGroup.addPreference(preference);
		}
	}
	
	@Override
	public Boolean isEnabled() {
		return InAppBillingAppModule.get() != null && (!InAppBillingAppModule.get().getInAppBillingContext().getManagedProductTypes().isEmpty()
				|| !InAppBillingAppModule.get().getInAppBillingContext().getSubscriptionsProductTypes().isEmpty());
	}
}
