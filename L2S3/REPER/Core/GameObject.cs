using System;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;

namespace REPER.Core;

public abstract class GameObject(ParticleGame context){

	protected const float GRAVITY_SPEED = 30f;

	protected readonly ParticleGame Context = context;

	// Object
	protected Vector2 Position = Vector2.Zero;
	protected float Scale = 0.1f;

	// Physics
	protected Vector2 Velocity = Vector2.Zero;
	protected Rectangle Bounds = new();
	protected BoundingSphere? Bounding = null;
	protected Vector2[] Boundings = [];
	protected bool Collided = false;
	private float _distanceToGround;

	// Texture
	protected Texture2D Texture;
	protected Vector2 TextureOrigin = Vector2.Zero;
	protected Color TextureColor = Color.White;

	// GETTERS
	public bool HasPhysics(){ return !Bounding.Equals(null); }

	public Vector2 GetPosition(){ return Position; }
	public Rectangle GetBounds(){ return Bounds; }

	public float GetDistanceToGround(){ return _distanceToGround; }

	// FUNCTIONS
	public abstract void Initialize();
	public abstract void LoadContent(ContentManager content);

	public abstract void Update(GameTime gameTime);
	public void Move(GameTime gameTime){
		if(!HasPhysics()) return;

		Position += Velocity*((float) gameTime.ElapsedGameTime.TotalSeconds);
		Velocity.Y += GRAVITY_SPEED;
	}
	public void Collide(GameObject gameObject){
		if(!HasPhysics()) return;

		if(!gameObject.HasPhysics()){
			var currBounding = Bounding.Value.Transform(new Matrix(
				    1,     0, 0, 0,
				    0,     1, 0, 0,
				    0,     0, 1, 0,
				Position.X, Position.Y, 0, 1
			));
			var pos = new Vector2(currBounding.Center.X, currBounding.Center.Y);

			for(int i = 0; i + 3 < gameObject.Boundings.Length; i += 3){
				var u = gameObject.Boundings[i]*gameObject.Scale;
				var uC = gameObject.Boundings[i + 1]*gameObject.Scale;
				var vC = gameObject.Boundings[i + 2]*gameObject.Scale;
				var v = gameObject.Boundings[i + 3]*gameObject.Scale;

				if(
					(pos.X + currBounding.Radius) >= u.X
					&& (pos.X - currBounding.Radius) <= v.X
				){
					var closest = closestBezier3(pos, u, uC, vC, v);

					_distanceToGround = Vector2.Distance(Position, closest);
					if(_distanceToGround < Bounding.Value.Radius){
						var dir = Vector2.Normalize(pos - closest);

						Position = closest + dir*Bounding.Value.Radius;
						Velocity = Vector2.Zero;
						Collided = true;
					}
				}
			}
		}
	}
	public void Draw(SpriteBatch spriteBatch, Vector2 origin){
		spriteBatch.Draw(
			Texture, Position - origin, Texture.Bounds, TextureColor,
			0f, TextureOrigin, Scale,
			SpriteEffects.None,
			0f
		);
	}

	// UTILS
	private static Vector2 Bezier2(float t,
		Vector2 u, Vector2 v, Vector2 w
	){
		return (1 - t)*((1 - t)*u + t*v) + t*((1 - t)*v + t*w);
	}
	protected static Vector2 Bezier3(float t,
		Vector2 u, Vector2 uC, Vector2 vC, Vector2 v
	){
		return (1 - t)*Bezier2(t, u, uC, vC) + t*Bezier2(t, uC, vC, v);
	}
	private static Vector2 closestBezier3(
		Vector2 position,
		Vector2 u, Vector2 uC, Vector2 vC, Vector2 v,
		int count = 100
	){
		float tSlice = 1f/count;
		Vector2 min = Bezier3(0, u, uC, vC, v);
		float dist = Vector2.Distance(position, min);

		for(int i = 1; i < count; i++){
			var point = Bezier3(tSlice*i, u, uC, vC, v);
			var currDist = Vector2.Distance(position, point);

			if(currDist < dist){
				min = point;
				dist = currDist;
			}
		}

		return min;
	}
}