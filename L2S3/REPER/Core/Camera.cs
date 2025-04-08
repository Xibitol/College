using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Content;

namespace REPER.Core;

public class Camera(
	ParticleGame context, GameObject tracked
): GameObject(context){

	private GameObject _tracked = tracked;
	private Rectangle _spaceBounds = new();

	// SETTERS
	public void SetSpaceBounds(Rectangle spaceBounds){
		_spaceBounds = spaceBounds;
	}

	// FUNCTIONS
	public override void Initialize(){
		Bounds = Context.GetViewport().Bounds;
	}
	public override void LoadContent(ContentManager content){}

	public override void Update(GameTime gameTime){
		Position = _tracked.GetPosition() - Bounds.Size.ToVector2()/2;

		if(Position.X < _spaceBounds.Left)
			Position.X = _spaceBounds.Left;
		else if((Position.X + Bounds.Width) > _spaceBounds.Right)
			Position.X = _spaceBounds.Right - Bounds.Width;

		if(Position.Y < _spaceBounds.Top)
			Position.Y = _spaceBounds.Top;
		else if((Position.Y + Bounds.Height) > _spaceBounds.Bottom)
			Position.Y = _spaceBounds.Bottom - Bounds.Height;
	}
}