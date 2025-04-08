using System;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;

namespace REPER.Core;

public class World(ParticleGame context): GameObject(context){

	private const string TEXTURE_ASSET_NAME = "world";

	// FUNCTIONS
	public override void Initialize(){
		Boundings = [
			new Vector2(109, 791), // 1
			new Vector2(86, 944),
			new Vector2(206, 1007),
			new Vector2(400, 1010), // 2
			new Vector2(566, 1009),
			new Vector2(735, 910),
			new Vector2(1002, 1011), // 3
			new Vector2(1474, 1138),
			new Vector2(1552, 889),
			new Vector2(1964, 897), // 4
			new Vector2(2149, 884),
			new Vector2(2223, 991),
			new Vector2(2605, 927), // 5
			new Vector2(2682, 916),
			new Vector2(2828, 958),
			new Vector2(2964, 856), // 6
			new Vector2(3147, 650),
			new Vector2(2939, 700),
			new Vector2(3043, 580), // 7
			new Vector2(3096, 499),
			new Vector2(3365, 524),
			new Vector2(3318, 1080)
		];
	}
	public override void LoadContent(ContentManager content){
		Texture = content.Load<Texture2D>(TEXTURE_ASSET_NAME);

		Scale = 1.01f;
		Bounds = Texture.Bounds;
	}

	public override void Update(GameTime gameTime){}
}