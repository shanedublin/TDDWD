package com.rusd.tddwd;

import javax.xml.soap.Text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Align;

public class GameMenu extends Stage {
	
	Skin skin;
	
	int buttonWidth = 270;
	int buttonHeight = 60;
	int padding = 9;
	
	public GameMenu() {
		Gdx.input.setInputProcessor(this);
		// A skin can be loaded via JSON or defined programmatically, either is fine. Using a skin is optional but strongly
		// recommended solely for the convenience of getting a texture, region, etc as a drawable, tinted drawable, etc.
		skin = new Skin();

		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		// Store the default libgdx font under the name "default".
		BitmapFont font = new BitmapFont();
		
		skin.add("default", new BitmapFont());

		// Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
		textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);
		
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = skin.getFont("default");
		skin.add("default", labelStyle);
		
//		skin.add("", resource);

		// Create a table that fills the screen. Everything else will go inside this table.
		Table table = new Table();
		table.setFillParent(true);
		this.addActor(table);

		final Label label = new Label("Paused", skin);
		label.setAlignment(Align.center);
		label.setFontScale(1.5f);
		table.add(label).width(buttonWidth).height(buttonHeight).pad(padding).colspan(3);
		table.row();
		
		final Label upgrades = new Label("Updgrades", skin);
		upgrades.setAlignment(Align.center);
		upgrades.setFontScale(1.5f);
		table.add(upgrades).width(buttonWidth).height(buttonHeight).pad(padding).colspan(3);
		
		// 
		
		
		
		
		TextButton bulletCount = new TextButton("Count", skin);
		TextButton bulletSpeed = new TextButton("Speed", skin);
		TextButton bulletDamage = new TextButton("Damage", skin);
		
		table.row();
		table.add(bulletCount).height(buttonHeight).width(buttonWidth/3-8).pad(8, 8, 8, 4);
		table.add(bulletSpeed).height(buttonHeight).width(buttonWidth/3-8).pad(8, 4, 8, 4);
		table.add(bulletDamage) .height(buttonHeight).width(buttonWidth/3-8).pad(8, 4, 8, 8);
		
		
		TextButton mineDamage = new TextButton("damage", skin);
		TextButton mineRadius = new TextButton("radius", skin);
		TextButton mineDuration = new TextButton("duration", skin);
		
		table.row();
		table.add(mineDamage).height(buttonHeight).width(buttonWidth/3-8).pad(8, 8, 8, 4);
		table.add(mineRadius).height(buttonHeight).width(buttonWidth/3-8).pad(8, 4, 8, 4);
		table.add(mineDuration).height(buttonHeight).width(buttonWidth/3-8).pad(8, 4, 8, 8);
		
		TextButton towerDuration = new TextButton("duration", skin);
		TextButton towerResource = new TextButton("resource", skin);
		TextButton towerSlow= new TextButton("slow", skin);
		
		table.row();
		table.add(towerDuration).height(buttonHeight).width(buttonWidth/3-8).pad(8, 8, 8, 4);
		table.add(towerResource).height(buttonHeight).width(buttonWidth/3-8).pad(8, 4, 8, 4);
		table.add(towerSlow).height(buttonHeight).width(buttonWidth/3-8).pad(8, 4, 8, 8);
		
		
//		table.row();
//		//Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
//		final TextButton button = new TextButton("Click me!", skin);		
//		table.add(button).width(buttonWidth).height(buttonHeight).pad(padding).colspan(3);


		// Add a listener to the button. ChangeListener is fired when the button's checked state changes, eg when clicked,
		// Button#setChecked() is called, via a key press, etc. If the event.cancel() is called, the checked state will be reverted.
		// ClickListener could have been used, but would only fire when clicked. Also, canceling a ClickListener event won't
		// revert the checked state.
//		button.addListener(new ChangeListener() {
//			public void changed (ChangeEvent event, Actor actor) {				
//				button.setText("Good job!");
//			}
//		});

		table.row();
		
		final TextButton quit = new TextButton("Quit", skin);
		table.add(quit).width(buttonWidth).height(buttonHeight).pad(padding).colspan(3);
		
		quit.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.app.exit();				
			}
		});
		
//		table.setDebug(true);
		
		// Add an image actor. Have to set the size, else it would be the size of the drawable (which is the 1x1 texture).
//		table.add(new Image(skin.newDrawable("white", Color.RED))).size(64);
	}

}
