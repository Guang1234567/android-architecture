package com.example.android.architecture.blueprints.todoapp.config;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.target.ViewTarget;

/**
 * Generates a Glide API for the Imgur sample.
 */
@GlideModule(glideName = "TodoGlide")
public class TodoGlideModule extends AppGlideModule {
  // Intentionally Empty.


    @Override
    public boolean isManifestParsingEnabled() {
        return false && super.isManifestParsingEnabled();
    }

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        super.applyOptions(context, builder);

        ViewTarget.setTagId(Integer.MIN_VALUE);
    }
}
