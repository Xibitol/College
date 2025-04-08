using System;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;

namespace REPER.Core;

public class Particle: GameObject{

	private const string TEXTURE_ASSET_NAME = "particle";
	private const float PARTICLE_SPEED = 350f;
	private const float JUMP_SPEED = 1000f;

	public Particle(ParticleGame context): base(context){
		Position = new Vector2(400, 950);
	}

	// Functions
	public override void Initialize(){}
	public override void LoadContent(ContentManager content){
		Texture = content.Load<Texture2D>(TEXTURE_ASSET_NAME);
		TextureOrigin = Texture.Bounds.Size.ToVector2()/2;
		TextureColor = Color.Black;

		Scale = 0.2f;
		Bounds = new Rectangle(
			(-Texture.Bounds.Size.ToVector2()*Scale/2).ToPoint(),
			(Texture.Bounds.Size.ToVector2()*Scale).ToPoint()
		);
		Bounding = new BoundingSphere(Vector3.Zero, Bounds.Width/2);
	}

	public override void Update(GameTime gameTime){
		KeyboardState kstate = Keyboard.GetState();

		// Reset
		if(kstate.IsKeyDown(Keys.R)){
			Position = new Vector2(400, 950);
			Velocity = Vector2.Zero;
		}

		// Jump
		if(Collided && kstate.IsKeyDown(Keys.Space)){
			Velocity.Y -= JUMP_SPEED;
			Collided = false;
		}

		// Levitation
		if(Collided){
			if(GetDistanceToGround() < GRAVITY_SPEED*2)
				Velocity.Y = -(GRAVITY_SPEED*2 - GetDistanceToGround())*5;
			else if(GetDistanceToGround() < GRAVITY_SPEED*5)
				Velocity.Y = -(GRAVITY_SPEED*2 - GetDistanceToGround())*10;
		}

		// Right & Left
		Vector2 movVec = Vector2.Zero;
		if(kstate.IsKeyDown(Keys.Left)) movVec.X -= 1;
		if(kstate.IsKeyDown(Keys.Right)) movVec.X += 1;

		if(!movVec.Equals(Vector2.Zero)) movVec.Normalize();
		Velocity.X = (Velocity.X + movVec.X*PARTICLE_SPEED)/2;
	}
}